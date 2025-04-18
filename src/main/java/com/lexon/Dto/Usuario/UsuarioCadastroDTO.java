package com.lexon.Dto.Usuario;

import java.util.List;

public class UsuarioCadastroDTO {
    private String nome;
    private List<PublicacaoDTO> publicacoes;

    public static class PublicacaoDTO {
        private String inscricaoOab;
        private String ufOab;
        private String nomeAdvogado;
        private String tipoInscOab;
        // Remover: private String siglaAgenciaTribunal;

        public String getInscricaoOab() {
            return inscricaoOab;
        }

        public void setInscricaoOab(String inscricaoOab) {
            this.inscricaoOab = inscricaoOab;
        }

        public String getUfOab() {
            return ufOab;
        }

        public void setUfOab(String ufOab) {
            this.ufOab = ufOab;
        }

        public String getNomeAdvogado() {
            return nomeAdvogado;
        }

        public void setNomeAdvogado(String nomeAdvogado) {
            this.nomeAdvogado = nomeAdvogado;
        }

        public String getTipoInscOab() {
            return tipoInscOab;
        }

        public void setTipoInscOab(String tipoInscOab) {
            this.tipoInscOab = tipoInscOab;
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PublicacaoDTO> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<PublicacaoDTO> publicacoes) {
        this.publicacoes = publicacoes;
    }
}