package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "agencia_tribunal")
public class AgenciaTribunal extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "sigla", unique = true, nullable = false)
    private String sigla;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "url_adm", nullable = false)
    private String urlAdm;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "agenciaTribunal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgenciaTribunalApi> apis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlAdm() {
        return urlAdm;
    }

    public void setUrlAdm(String urlAdm) {
        this.urlAdm = urlAdm;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<AgenciaTribunalApi> getApis() {
        return apis;
    }

    public void setApis(List<AgenciaTribunalApi> apis) {
        this.apis = apis;
    }
}