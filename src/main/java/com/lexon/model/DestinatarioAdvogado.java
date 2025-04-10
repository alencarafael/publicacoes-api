package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "destinatario_advogado")
public class DestinatarioAdvogado extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long id_publicacao;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "comunicacao_id", nullable = false)
    public Comunicacao comunicacao;

    @ManyToOne
    @JoinColumn(name = "advogado_id", nullable = false)
    public Advogado advogado;
}


