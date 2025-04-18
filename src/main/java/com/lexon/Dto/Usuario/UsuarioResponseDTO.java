package com.lexon.Dto.Usuario;

import java.util.List;
import java.util.UUID;

public class UsuarioResponseDTO {
    private UUID id;
    private String nome;
    private List<PublicacaoResponseDTO> publicacoes;

    public UsuarioResponseDTO(UUID id, String nome, List<PublicacaoResponseDTO> publicacoes) {
        this.id = id;
        this.nome = nome;
        this.publicacoes = publicacoes;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<PublicacaoResponseDTO> getPublicacoes() {
        return publicacoes;
    }
}