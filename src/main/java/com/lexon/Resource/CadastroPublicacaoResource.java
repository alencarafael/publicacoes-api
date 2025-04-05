package com.lexon.Resource;

import com.lexon.Client.OabClient;
import com.lexon.Dto.CadastroPublicacaoDto;
import com.lexon.model.CadastroPublicacao;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/cadastro-publicacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CadastroPublicacaoResource {

    @Inject
    @RestClient
    OabClient oabClient;

    @POST
    @Transactional
    public Response salvar(CadastroPublicacaoDto dto) {

        // Buscar o nome do advogado na API da OAB
        OabClient.OabResponse response = oabClient.search(dto.inscricaoOab, dto.seccional);
        if (response.Success && response.Data != null && !response.Data.isEmpty()) {
            dto.nomeAdvogado = response.Data.get(0).Nome;
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Advogado não encontrado na OAB.")
                    .build();
        }

        CadastroPublicacao i = new CadastroPublicacao();
        i.inscricaoOab = dto.inscricaoOab;
        i.ufOab = dto.seccional;
        i.nomeAdvogado = dto.nomeAdvogado;
        i.id = java.util.UUID.randomUUID();
//        i.validacao = "ALTOMATICO";
        i.persist();

        return Response.status(Response.Status.CREATED).entity(i).build();
    }


}
