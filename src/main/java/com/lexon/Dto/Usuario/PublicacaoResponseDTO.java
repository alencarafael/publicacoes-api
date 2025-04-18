package com.lexon.Dto.Usuario;


import java.util.UUID;

public class PublicacaoResponseDTO {
    private UUID id;
    private String inscricaoOab;
    private String ufOab;
    private String nomeAdvogado;
    private String tipoInscOab;
    private String siglaAgenciaTribunal;

    public PublicacaoResponseDTO(UUID id, String inscricaoOab, String ufOab, String nomeAdvogado, String tipoInscOab, String siglaAgenciaTribunal) {
        this.id = id;
        this.inscricaoOab = inscricaoOab;
        this.ufOab = ufOab;
        this.nomeAdvogado = nomeAdvogado;
        this.tipoInscOab = tipoInscOab;
        this.siglaAgenciaTribunal = siglaAgenciaTribunal;
    }

    public UUID getId() {
        return id;
    }

    public String getInscricaoOab() {
        return inscricaoOab;
    }

    public String getUfOab() {
        return ufOab;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public String getTipoInscOab() {
        return tipoInscOab;
    }

    public String getSiglaAgenciaTribunal() {
        return siglaAgenciaTribunal;
    }
}