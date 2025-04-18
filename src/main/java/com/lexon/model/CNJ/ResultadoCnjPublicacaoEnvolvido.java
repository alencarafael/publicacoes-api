package com.lexon.model.CNJ;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "resultado_cnj_publicacao_envolvidos")
public class ResultadoCnjPublicacaoEnvolvido extends PanacheEntityBase {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cnj_id")
    private ResultadoCnj resultadoCnj;

    @ManyToOne(optional = false)
    @JoinColumn(name = "envolvidos_id")
    private ResultadoCnjEnvolvido envolvido;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ResultadoCnj getResultadoCnj() {
        return resultadoCnj;
    }

    public void setResultadoCnj(ResultadoCnj resultadoCnj) {
        this.resultadoCnj = resultadoCnj;
    }

    public ResultadoCnjEnvolvido getEnvolvido() {
        return envolvido;
    }

    public void setEnvolvido(ResultadoCnjEnvolvido envolvido) {
        this.envolvido = envolvido;
    }
}
