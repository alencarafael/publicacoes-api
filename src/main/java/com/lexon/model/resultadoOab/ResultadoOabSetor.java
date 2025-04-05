package com.lexon.model.resultadoOab;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "resultado_oab_setor")
public class ResultadoOabSetor extends PanacheEntityBase {

    @Id
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "resultado_oab_organizacao_id", nullable = false)
    public ResultadoOabOrganizacao organizacao;

    public Integer setor;

    @Column(name = "nome_alternativo")
    public String nomeAlternativo;

    @Column(name = "nome_original")
    public String nomeOriginal;

    public Integer ordem;
}
