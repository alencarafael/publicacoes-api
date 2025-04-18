package com.lexon.Resource;

import com.lexon.Dto.Usuario.UsuarioCadastroDTO;
import com.lexon.Dto.Usuario.UsuarioResponseDTO;
import com.lexon.Service.UsuarioService;
import com.lexon.model.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    public Response cadastrar(UsuarioCadastroDTO dto) {
        Usuario entity = service.salvar(dto);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @GET
    public Response listar() {
        List<UsuarioResponseDTO> lista = service.listarTodos();
        return Response.ok(lista).build();
    }
}