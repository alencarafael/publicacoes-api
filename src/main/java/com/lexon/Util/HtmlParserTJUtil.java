package com.lexon.Util;

import com.lexon.Dto.ProcessoTJResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParserTJUtil {

    public static class NumeroProcessoInfo {
        public String numero;
        public String foro;
        public String codigo = "9E0004NJK0000";
        public String digitoAno;
        public String tribunal;

        public NumeroProcessoInfo(String numeroProcesso) {
            this.numero = numeroProcesso;
            String[] partes = numeroProcesso.split("\\.");
            this.digitoAno = partes[0] + "." + partes[1];
            this.tribunal = partes[3];
            this.foro = numeroProcesso.substring(numeroProcesso.length() - 4);
        }
    }

    public static NumeroProcessoInfo extrairInfo(String numeroProcesso) {
        return new NumeroProcessoInfo(numeroProcesso);
    }

    public static ProcessoTJResponseDTO consultarPrimeiroGrau(NumeroProcessoInfo info, String dominio) throws IOException {
        String url = "https://" + dominio + "/cpopg/show.do?processo.foro=" + info.foro + "&processo.codigo=" + info.codigo + "&processo.numero=" + info.numero;
        Document html = Jsoup.connect(url).get();
        return parseProcesso(html, info.numero);
    }

    public static ProcessoTJResponseDTO consultarSegundoGrau(NumeroProcessoInfo info, String dominio) throws IOException {
        String searchUrl = "https://" + dominio + "/cposg5/search.do?cbPesquisa=NUMPROC&numeroDigitoAnoUnificado=" + info.digitoAno + "&foroNumeroUnificado=" + info.foro + "&dePesquisaNuUnificado=" + info.numero;
        Document searchHtml = Jsoup.connect(searchUrl).get();
        Element input = searchHtml.getElementById("processoSelecionado");
        if (input == null) return null;
        String codigo = input.attr("value");

        String showUrl = "https://" + dominio + "/cposg5/show.do?processo.codigo=" + codigo;
        Document html = Jsoup.connect(showUrl).get();
        return parseProcesso(html, info.numero);
    }

    private static ProcessoTJResponseDTO parseProcesso(Document html, String numeroProcesso) {
        ProcessoTJResponseDTO dto = new ProcessoTJResponseDTO();
        dto.numeroProcesso = numeroProcesso;

        dto.classe = getTextById(html, "classeProcesso");
        dto.area = getTextById(html, "areaProcesso");
        dto.assunto = getTextById(html, "assuntoProcesso");
        dto.juiz = getTextById(html, "juizProcesso");
        dto.dataDistribuicao = getTextById(html, "dataHoraDistribuicaoProcesso").split(" ")[0];
        dto.valorAcao = getTextById(html, "valorAcaoProcesso");

        dto.partes = extrairPartes(html);
        dto.movimentacoes = extrairMovimentacoes(html);

        return dto;
    }

    private static List<ProcessoTJResponseDTO.ParteDTO> extrairPartes(Document html) {
        List<ProcessoTJResponseDTO.ParteDTO> partes = new ArrayList<>();
        Elements linhas = html.select("#tableTodasPartes tr, #tablePartesPrincipais tr");

        for (Element linha : linhas) {
            Element nomeEl = linha.selectFirst(".nomeParteEAdvogado");
            Element tipoEl = linha.selectFirst(".tipoDeParticipacao");
            if (nomeEl == null || tipoEl == null) continue;

            ProcessoTJResponseDTO.ParteDTO parte = new ProcessoTJResponseDTO.ParteDTO();
            String[] split = nomeEl.text().split(" Advogado: | Advogada: ");
            parte.nome = split[0].trim();
            parte.tipoDeParticipacao = tipoEl.text().trim();
            parte.advogados = new ArrayList<>();
            for (int i = 1; i < split.length; i++) parte.advogados.add(split[i].trim());
            partes.add(parte);
        }
        return partes;
    }

    private static List<ProcessoTJResponseDTO.MovimentacaoDTO> extrairMovimentacoes(Document html) {
        List<ProcessoTJResponseDTO.MovimentacaoDTO> movimentos = new ArrayList<>();
        Elements blocos = html.select(".containerMovimentacao, .movimentacaoProcesso");

        for (Element bloco : blocos) {
            Element dataEl = bloco.selectFirst(".dataMovimentacao, .dataMovimentacaoProcesso");
            Element descEl = bloco.selectFirst(".descricaoMovimentacao, .descricaoMovimentacaoProcesso");
            if (dataEl == null || descEl == null) continue;

            ProcessoTJResponseDTO.MovimentacaoDTO mov = new ProcessoTJResponseDTO.MovimentacaoDTO();
            mov.data = dataEl.text().trim();
            mov.descricao = descEl.text().trim();
            movimentos.add(mov);
        }
        return movimentos;
    }

    private static String getTextById(Document html, String id) {
        Element el = html.getElementById(id);
        return el != null ? el.text().trim() : "";
    }
}
