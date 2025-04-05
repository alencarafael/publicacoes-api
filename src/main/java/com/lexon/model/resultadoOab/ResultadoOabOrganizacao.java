package com.lexon.model.resultadoOab;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "resultado_oab_organizacao")
public class ResultadoOabOrganizacao extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(name = "id_organizacao")
    public Integer idOrganizacao;

    @Column(name = "nome_alternativo")
    public String nomeAlternativo;

    public String ordem;
    public String pagina;
    public String local;
}
