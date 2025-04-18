package com.lexon.model.CNJ;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "resultado_cnj_envolvidos")
public class ResultadoCnjEnvolvido extends PanacheEntityBase {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // 'DESTINATARIO' ou 'ADVOGADO'

    private String polo;
    private String numeroOab;

    @Column(length = 2)
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
