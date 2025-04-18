package com.lexon.Dto.CNJ;

import java.util.UUID;

public class ResultadoCnjEnvolvidoDTO {

    private UUID id;
    private String nome;
    private String tipo; // ADVOGADO ou DESTINATARIO
    private String polo;
    private String numeroOab;
    private String ufOab;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPolo() {
        return polo;
    }

    public void setPolo(String polo) {
        this.polo = polo;
    }

    public String getNumeroOab() {
        return numeroOab;
    }

    public void setNumeroOab(String numeroOab) {
        this.numeroOab = numeroOab;
    }

    public String getUfOab() {
        return ufOab;
    }

    public void setUfOab(String ufOab) {
        this.ufOab = ufOab;
    }
}