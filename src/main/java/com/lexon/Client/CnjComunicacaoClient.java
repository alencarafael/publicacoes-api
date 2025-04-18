package com.lexon.Client;

import com.lexon.Dto.CNJ.ComunicacaoResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/v1/comunicacao")
@Produces(MediaType.APPLICATION_JSON)
public interface CnjComunicacaoClient {

    @GET
    ComunicacaoResponseDTO consultar(
            @QueryParam("numeroOab") String numeroOab,
            @QueryParam("ufOab") String ufOab,
            @QueryParam("nomeAdvogado") String nomeAdvogado
    );
}