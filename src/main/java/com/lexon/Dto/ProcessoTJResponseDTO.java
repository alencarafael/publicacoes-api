package com.lexon.Dto;

import java.util.List;

public class ProcessoTJResponseDTO {
    public String numeroProcesso;
    public String classe;
    public String area;
    public String assunto;
    public String juiz;
    public String dataDistribuicao;
    public String valorAcao;
    public List<ParteDTO> partes;
    public List<MovimentacaoDTO> movimentacoes;

    public static class ParteDTO {
        public String nome;
        public String tipoDeParticipacao;
        public List<String> advogados;
    }

    public static class MovimentacaoDTO {
        public String data;
        public String descricao;
    }
}
