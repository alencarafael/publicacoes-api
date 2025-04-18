package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "agencia_tribunal_api")
public class AgenciaTribunalApi extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "agencia_tribunal_id", nullable = false)
    private AgenciaTribunal agenciaTribunal;

    @Column(name = "url_api", nullable = false)
    private String urlApi;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public AgenciaTribunal getAgenciaTribunal() {
        return agenciaTribunal;
    }

    public void setAgenciaTribunal(AgenciaTribunal agenciaTribunal) {
        this.agenciaTribunal = agenciaTribunal;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
