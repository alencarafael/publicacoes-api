package com.lexon.Client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/Home")
@RegisterRestClient(configKey="oab-api")
public interface OabClient {

    @GET
    @Path("/Search")
    OabResponse search(@QueryParam("insc") String inscricao, @QueryParam("uf") String uf);

    class OabResponse {
        public boolean Success;
        public List<Data> Data;

        public static class Data {
            public String Nome;
            public String TipoInscOab;
            public String Inscricao;
            public String UF;
            public String NomeSocial;
            public String DetailUrl;
        }
    }
}