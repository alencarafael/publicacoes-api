package com.lexon.Service;

import com.lexon.Client.DeoabClient;
import com.lexon.model.CadastroPublicacao;
import com.lexon.model.resultadoOab.ResultadoOab;
import com.lexon.model.resultadoOab.ResultadoOabOrganizacao;
import com.lexon.model.resultadoOab.ResultadoOabSetor;
import com.lexon.model.resultadoOab.ResultadoOabTipo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jsoup.Jsoup;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DeoabSyncService {

    @Inject
    @RestClient
    DeoabClient deoabClient;

    @Transactional
    public void sincronizarPublicacoes(String nomeAdvogado, int idOrganizacao, String inicio, String fim,
                                       CadastroPublicacao cadastroPublicacao) {

        var response = deoabClient.pesquisarPublicacoes(1, nomeAdvogado, idOrganizacao, inicio, fim);

        for (var resumo : response.itens) {
            var detalhe = deoabClient.buscarDetalhePublicacao(resumo.id);

            ResultadoOabTipo tipo = persistirTipo(detalhe.tipoPublicacao);
            ResultadoOabOrganizacao organizacao = persistirOrganizacao(detalhe.setorPublicacao.organizacao);
            ResultadoOabSetor setor = persistirSetor(detalhe.setorPublicacao, organizacao);

            ResultadoOab resultado = new ResultadoOab();
            resultado.id = UUID.randomUUID();
            resultado.cadastroPublicacao = cadastroPublicacao;
//            resultado.tribunal = tribunal;
            resultado.tipo = tipo;
            resultado.setor = setor;
            resultado.idPublicacao = detalhe.id;
            resultado.ano = detalhe.ano;
            resultado.issn = detalhe.issn;
            resultado.titulo = detalhe.titulo;
            resultado.conteudoCodificado = detalhe.conteudoCodificado;
            resultado.conteudo = Jsoup.parse(detalhe.conteudoCodificado).text(); // Conteúdo limpo
            resultado.localPublicacao = detalhe.local;
            resultado.dataPublicacao = LocalDate.parse(detalhe.dataPublicacao);
            resultado.pagina = detalhe.pagina;
            resultado.edicao = detalhe.edicao;
            resultado.edicaoExtra = detalhe.edicaoExtra;

            resultado.persist();
        }
    }

    private ResultadoOabTipo persistirTipo(DeoabClient.TipoPublicacao tipoDTO) {
        return Optional.ofNullable(ResultadoOabTipo.findById(UUID.nameUUIDFromBytes(("tipo:" + tipoDTO.id).getBytes())))
                .map(t -> (ResultadoOabTipo) t)
                .orElseGet(() -> {
                    ResultadoOabTipo tipo = new ResultadoOabTipo();
                    tipo.id = UUID.nameUUIDFromBytes(("tipo:" + tipoDTO.id).getBytes());
                    tipo.descricao = tipoDTO.descricao;
                    tipo.ordem = tipoDTO.ordem;
                    tipo.persist();
                    return tipo;
                });
    }

    private ResultadoOabOrganizacao persistirOrganizacao(DeoabClient.Organizacao orgDTO) {
        return Optional.ofNullable(ResultadoOabOrganizacao.findById(UUID.nameUUIDFromBytes(("org:" + orgDTO.id).getBytes())))
                .map(o -> (ResultadoOabOrganizacao) o)
                .orElseGet(() -> {
                    ResultadoOabOrganizacao org = new ResultadoOabOrganizacao();
                    org.id = UUID.nameUUIDFromBytes(("org:" + orgDTO.id).getBytes());
                    org.idOrganizacao = orgDTO.id;
                    org.nomeAlternativo = orgDTO.nomeAlternativo;
                    org.ordem = orgDTO.ordem;
                    org.pagina = orgDTO.pagina;
                    org.local = orgDTO.local;
                    org.persist();
                    return org;
                });
    }

    private ResultadoOabSetor persistirSetor(DeoabClient.SetorPublicacao setorDTO, ResultadoOabOrganizacao org) {
        String uid = "setor:" + setorDTO.setor + ":org:" + org.id;
        return Optional.ofNullable(ResultadoOabSetor.findById(UUID.nameUUIDFromBytes(uid.getBytes())))
                .map(s -> (ResultadoOabSetor) s)
                .orElseGet(() -> {
                    ResultadoOabSetor setor = new ResultadoOabSetor();
                    setor.id = UUID.nameUUIDFromBytes(uid.getBytes());
                    setor.organizacao = org;
                    setor.setor = setorDTO.setor;
                    setor.nomeAlternativo = setorDTO.nomeAlternativo;
                    setor.nomeOriginal = setorDTO.nomeOriginal;
                    setor.ordem = setorDTO.ordem;
                    setor.persist();
                    return setor;
                });
    }
}
