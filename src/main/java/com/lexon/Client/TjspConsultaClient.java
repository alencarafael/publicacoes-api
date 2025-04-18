package com.lexon.Client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/cpopg/search.do")
@Produces(MediaType.TEXT_HTML)
public interface TjspConsultaClient {

    @GET
    String consultarPorOab(
            @QueryParam("cbPesquisa") String cbPesquisa,
            @QueryParam("numOAB") String numOab // formato: 3265SP
    );
}
