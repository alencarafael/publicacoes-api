package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cadastro_publicacao")
public class CadastroPublicacao extends PanacheEntityBase {

    @Id
    private UUID id;

    @Column(name = "inscricao_oab", nullable = false)
    private String inscricaoOab;

    @Column(name = "uf_oab", nullable = false, length = 2)
    private String ufOab;

    @Column(name = "nome_advogado", nullable = false)
    private String nomeAdvogado;

    @Column(name = "tipo_insc_oab", nullable = false)
    private String tipoInscOab;

    @Column(name = "sigla_agencia_tribunal", nullable = false)
    private String siglaAgenciaTribunal;

    @ManyToMany(mappedBy = "publicacoes")
    private List<Usuario> usuarios;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getSiglaAgenciaTribunal() {
        return siglaAgenciaTribunal;
    }

    public void setSiglaAgenciaTribunal(String siglaAgenciaTribunal) {
        this.siglaAgenciaTribunal = siglaAgenciaTribunal;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
