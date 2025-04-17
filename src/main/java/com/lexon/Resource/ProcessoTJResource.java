package com.lexon.Resource;

import com.lexon.Dto.ProcessoTJResponseDTO;
import com.lexon.Service.ProcessoTJService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/consulta-tj")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProcessoTJResource {

    @Inject
    ProcessoTJService processoTJService;

    @GET
    @Path("/primeiro-grau")
    @Operation(summary = "Consulta processo em 1º grau", description = "Consulta os dados de um processo judicial no TJ em 1º grau via scraping.")
    public Response consultarPrimeiroGrau(@QueryParam("numeroProcesso") String numeroProcesso) {
        ProcessoTJResponseDTO response = processoTJService.buscarPrimeiroGrau(numeroProcesso);
        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Processo não encontrado").build();
        }
        return Response.ok(response).build();
    }

    @GET
    @Path("/segundo-grau")
    @Operation(summary = "Consulta processo em 2º grau", description = "Consulta os dados de um processo judicial no TJ em 2º grau via scraping.")
    public Response consultarSegundoGrau(@QueryParam("numeroProcesso") String numeroProcesso) {
        ProcessoTJResponseDTO response = processoTJService.buscarSegundoGrau(numeroProcesso);
        if (response == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Processo não encontrado").build();
        }
        return Response.ok(response).build();
    }
}
