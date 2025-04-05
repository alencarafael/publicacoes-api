package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tribunal")
public class Tribunal extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(length = 2, nullable = false)
    public String uf;

    @Column(name = "nome_uf", nullable = false)
    public String nomeUf;

    @Column(name = "sigla_tribunal", nullable = false)
    public String siglaTribunal;

    @Column(name = "nome_tribunal", nullable = false)
    public String nomeTribunal;

    @Column(name = "url_tribunal")
    public String urlTribunal;

    @Column(name = "status", nullable = false)
    public String status;

    @ManyToMany(mappedBy = "tribunais")
    public List<CadastroPublicacao> cadastroPublicacoes;
}