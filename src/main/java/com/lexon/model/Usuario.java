package com.lexon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "cadastro_publicacao_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cadastro_publicacao_id")
    )
    private List<CadastroPublicacao> publicacoes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CadastroPublicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<CadastroPublicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
}
