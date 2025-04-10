package com.lexon.Client;

import com.lexon.Dto.ComunicaResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/v1/comunicacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "comunica-api")
public interface ComunicaApiClient {

    @GET
    ComunicaResponseDTO consultarComunicacoes(
            @QueryParam("numeroOab") String numeroOab,
            @QueryParam("ufOab") String ufOab,
            @QueryParam("nomeAdvogado") String nomeAdvogado,
            @QueryParam("dataDisponibilizacaoInicio") String dataInicio,
            @QueryParam("dataDisponibilizacaoFim") String dataFim
    );
}