package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(nullable = false)
    public String nome;

    @ManyToMany(mappedBy = "usuarios")
    public List<CadastroPublicacao> cadastroPublicacoes;
}

