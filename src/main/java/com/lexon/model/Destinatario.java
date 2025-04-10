package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "destinatario")
public class Destinatario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long id_publicacao;

    public String nome;
    public String polo;

    @ManyToOne
    @JoinColumn(name = "comunicacao_id", nullable = false)
    public Comunicacao comunicacao;
}

