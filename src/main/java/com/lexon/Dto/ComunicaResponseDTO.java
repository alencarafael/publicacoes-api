package com.lexon.Dto;


import java.util.List;

public class ComunicaResponseDTO {
    public String status;
    public String message;
    public Integer count;
    public List<ItemDTO> items;

    public static class ItemDTO {
        public Integer id;
        public String data_disponibilizacao;
        public String siglaTribunal;
        public String tipoComunicacao;
        public String nomeOrgao;
        public String texto;
        public String numero_processo;
        public String meio;
        public String link;
        public String tipoDocumento;
        public String nomeClasse;
        public String codigoClasse;
        public Integer numeroComunicacao;
        public boolean ativo;
        public String hash;
        public String datadisponibilizacao;
        public String meiocompleto;
        public String numeroprocessocommascara;
        public List<DestinatarioDTO> destinatarios;
        public List<DestinatarioAdvogadoDTO> destinatarioadvogados;
    }

    public static class DestinatarioDTO {
        public String nome;
        public String polo;
        public Integer comunicacao_id;
    }

    public static class DestinatarioAdvogadoDTO {
        public Integer id;
        public Integer comunicacao_id;
        public Integer advogado_id;
        public String created_at;
        public String updated_at;
        public AdvogadoDTO advogado;
    }

    public static class AdvogadoDTO {
        public Integer id;
        public String nome;
        public String numero_oab;
        public String uf_oab;
    }
}