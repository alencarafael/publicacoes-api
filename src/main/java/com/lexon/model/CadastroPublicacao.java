package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cadastro_publicacao")
public class CadastroPublicacao extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(name = "inscricao_oab", nullable = false)
    public String inscricaoOab;

    @Column(name = "uf_oab", nullable = false)
    public String ufOab;

    @Column(name = "nome_advogado", nullable = false)
    public String nomeAdvogado;

//    @Column(name = "validacao", nullable = false)
//    public String validacao;

    @ManyToMany
    @JoinTable(
            name = "cadastro_publicacao_usuario",
            joinColumns = @JoinColumn(name = "cadastro_publicacao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    public List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "cadastro_publicacao_tribunal",
            joinColumns = @JoinColumn(name = "cadastro_publicacao_id"),
            inverseJoinColumns = @JoinColumn(name = "tribunal_id")
    )
    public List<Tribunal> tribunais;

}