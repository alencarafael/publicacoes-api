package com.lexon.Resource;

import com.lexon.model.Comunicacao;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/consulta-db")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaDBResource {

    @GET
    @Transactional
    public Response consultarPorBanco(
            @QueryParam("numeroOab") String numeroOab,
            @QueryParam("ufOab") String ufOab,
            @QueryParam("nomeAdvogado") String nomeAdvogado
    ) {
        List<Comunicacao> comunicacoes = Comunicacao.listAll();

        List<Object> resultado = comunicacoes.stream()
                // Ordenar pela dataDisponibilizacao (descendente)
                .sorted((c1, c2) -> c2.dataDisponibilizacao.compareTo(c1.dataDisponibilizacao))
                .filter(comunicacao ->
                        comunicacao.destinatarioAdvogados.stream().anyMatch(da ->
                                da.advogado != null &&
                                        (numeroOab == null || da.advogado.numeroOab.equalsIgnoreCase(numeroOab)) &&
                                        (ufOab == null || da.advogado.ufOab.equalsIgnoreCase(ufOab)) &&
                                        (nomeAdvogado == null || da.advogado.nome.toLowerCase().contains(nomeAdvogado.toLowerCase()))
                        )
                ).map(comunicacao -> {
                    return new java.util.HashMap<String, Object>() {{
                        // Informação principal
                        put("texto", comunicacao.texto);
                        put("numeroProcesso", comunicacao.numeroProcesso);
                        put("dataDisponibilizacao", comunicacao.dataDisponibilizacao);

                        // Informações adicionais para o lado esquerdo
                        put("nomeOrgao", comunicacao.nomeOrgao);
                        put("tipoComunicacao", comunicacao.tipoComunicacao);
                        put("meio", comunicacao.meio);
                        put("link", comunicacao.link);

                        // Destinatários e advogados relacionados
                        put("destinatarios", comunicacao.destinatarios.stream().map(dest -> new java.util.HashMap<String, Object>() {{
                            put("nome", dest.nome);
                        }}).collect(Collectors.toList()));

                        put("destinatarioAdvogados", comunicacao.destinatarioAdvogados.stream().map(da -> new java.util.HashMap<String, Object>() {{
                            put("advogado_id", da.advogado != null ? da.advogado.id : null);
                            put("advogado", da.advogado != null ? new java.util.HashMap<String, Object>() {{
                                put("nome", da.advogado.nome);
                                put("numero_oab", da.advogado.numeroOab);
                            }} : null);
                        }}).collect(Collectors.toList()));
                    }};
                }).collect(Collectors.toList());

        return Response.ok(resultado).build();
    }
}