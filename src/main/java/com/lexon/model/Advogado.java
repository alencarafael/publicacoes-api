package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "advogado")
public class Advogado extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long id_publicacao;

    public String nome;

    @Column(name = "numero_oab")
    public String numeroOab;

    @Column(name = "uf_oab")
    public String ufOab;
}
