package com.lexon.model.resultadoOab;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "resultado_oab_tipo")
public class ResultadoOabTipo extends PanacheEntityBase {

    @Id
    public UUID id;

    public String descricao;

    public Integer ordem;
}
