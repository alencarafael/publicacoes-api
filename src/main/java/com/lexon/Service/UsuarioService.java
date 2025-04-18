package com.lexon.Service;

import com.lexon.Dto.Usuario.PublicacaoResponseDTO;
import com.lexon.Dto.Usuario.UsuarioCadastroDTO;
import com.lexon.Dto.Usuario.UsuarioResponseDTO;
import com.lexon.Util.SiglaAgenciaTribunal;
import com.lexon.model.CadastroPublicacao;
import com.lexon.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Transactional
    public Usuario salvar(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNome(dto.getNome());

        List<CadastroPublicacao> publicacoes = new ArrayList<>();

        for (UsuarioCadastroDTO.PublicacaoDTO pubDto : dto.getPublicacoes()) {
            CadastroPublicacao pub = new CadastroPublicacao();
            pub.setId(UUID.randomUUID());
            pub.setInscricaoOab(pubDto.getInscricaoOab());
            pub.setUfOab(pubDto.getUfOab());
            pub.setNomeAdvogado(pubDto.getNomeAdvogado());
            pub.setTipoInscOab(pubDto.getTipoInscOab());

            // Preenchendo automaticamente o campo siglaAgenciaTribunal
            String siglasConcatenadas = Arrays.stream(SiglaAgenciaTribunal.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(","));

            pub.setSiglaAgenciaTribunal(siglasConcatenadas);
            pub.persist();

            publicacoes.add(pub);
        }

        usuario.setPublicacoes(publicacoes);
        usuario.persist();

        return usuario;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return Usuario.<Usuario>listAll().stream()
                .map(usuario -> {
                    List<PublicacaoResponseDTO> publicacoes = usuario.getPublicacoes().stream()
                            .map(pub -> new PublicacaoResponseDTO(
                                    pub.getId(),
                                    pub.getInscricaoOab(),
                                    pub.getUfOab(),
                                    pub.getNomeAdvogado(),
                                    pub.getTipoInscOab(),
                                    pub.getSiglaAgenciaTribunal()
                            ))
                            .collect(Collectors.toList());

                    return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), publicacoes);
                })
                .collect(Collectors.toList());
    }
}
