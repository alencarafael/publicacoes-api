package com.lexon.Resource;

import com.lexon.Client.ComunicaApiClient;
import com.lexon.Dto.ComunicaResponseDTO;
import com.lexon.Dto.ConsultaResponseDTO;
import com.lexon.model.Advogado;
import com.lexon.model.Comunicacao;
import com.lexon.model.Destinatario;
import com.lexon.model.DestinatarioAdvogado;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    @RestClient
    ComunicaApiClient comunicaApiClient;

    @GET
    @Transactional
    @Operation(
            summary = "Consulta publicações na API do PJe",
            description = "Consulta publicações por número da OAB, UF, nome do advogado e intervalo de datas"
    )
    public Response consultar(
            @QueryParam("numeroOab") String numeroOab,
            @QueryParam("ufOab") String ufOab,
            @QueryParam("nomeAdvogado") String nomeAdvogado,
            @QueryParam("dataDisponibilizacaoInicio") String dataInicio,
            @QueryParam("dataDisponibilizacaoFim") String dataFim
    ) {
        ComunicaResponseDTO response = comunicaApiClient.consultarComunicacoes(numeroOab, ufOab, nomeAdvogado, dataInicio, dataFim);

        if (response == null || response.items.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("Nenhuma comunicação encontrada").build();
        }

        for (ComunicaResponseDTO.ItemDTO item : response.items) {
            Comunicacao comunicacao = processarComunicacao(item);
            comunicacao.persist();
        }

        return Response.ok(new ConsultaResponseDTO("success", "Dados salvos com sucesso", response.items)).build();
    }

    private Comunicacao processarComunicacao(ComunicaResponseDTO.ItemDTO item) {
        Comunicacao comunicacao = new Comunicacao();

        comunicacao.id_publicacao = Long.valueOf(item.id);
        comunicacao.dataDisponibilizacao = item.data_disponibilizacao;
        comunicacao.siglaTribunal = item.siglaTribunal;
        comunicacao.tipoComunicacao = item.tipoComunicacao;
        comunicacao.nomeOrgao = item.nomeOrgao;
        comunicacao.texto = item.texto;
        comunicacao.numeroProcesso = item.numero_processo;
        comunicacao.meio = item.meio;
        comunicacao.link = item.link;
        comunicacao.tipoDocumento = item.tipoDocumento;
        comunicacao.nomeClasse = item.nomeClasse;
        comunicacao.codigoClasse = item.codigoClasse;
        comunicacao.numeroComunicacao = item.numeroComunicacao;
        comunicacao.ativo = item.ativo;
        comunicacao.hash = item.hash;
        comunicacao.meiocompleto = item.meiocompleto;
        comunicacao.numeroProcessoComMascara = item.numeroprocessocommascara;

        comunicacao.destinatarios = processarDestinatarios(item.destinatarios, comunicacao);
        comunicacao.destinatarioAdvogados = processarDestinatarioAdvogados(item.destinatarioadvogados, comunicacao);

        return comunicacao;
    }

    private List<Destinatario> processarDestinatarios(List<ComunicaResponseDTO.DestinatarioDTO> destinatariosDTO, Comunicacao comunicacao) {
        List<Destinatario> destinatarios = new ArrayList<>();

        for (ComunicaResponseDTO.DestinatarioDTO destDTO : destinatariosDTO) {
            Destinatario destinatario = new Destinatario();
            destinatario.id_publicacao = comunicacao.id_publicacao;
            destinatario.nome = destDTO.nome;
            destinatario.polo = destDTO.polo;
            destinatario.comunicacao = comunicacao;

            destinatarios.add(destinatario);
        }

        return destinatarios;
    }

    private List<DestinatarioAdvogado> processarDestinatarioAdvogados(List<ComunicaResponseDTO.DestinatarioAdvogadoDTO> destinatariosAdvogadosDTO, Comunicacao comunicacao) {
        List<DestinatarioAdvogado> destinatarioAdvogados = new ArrayList<>();

        for (ComunicaResponseDTO.DestinatarioAdvogadoDTO dto : destinatariosAdvogadosDTO) {
            DestinatarioAdvogado destinatarioAdvogado = new DestinatarioAdvogado();

            destinatarioAdvogado.id_publicacao = comunicacao.id_publicacao;
            destinatarioAdvogado.comunicacao = comunicacao;
            destinatarioAdvogado.createdAt = parseDateTime(dto.created_at);
            destinatarioAdvogado.updatedAt = parseDateTime(dto.updated_at);

            Advogado advogado = new Advogado();
            advogado.id_publicacao = comunicacao.id_publicacao;
            advogado.nome = dto.advogado.nome;
            advogado.numeroOab = dto.advogado.numero_oab;
            advogado.ufOab = dto.advogado.uf_oab;

            Advogado.persist(advogado);
            destinatarioAdvogado.advogado = advogado;
            destinatarioAdvogados.add(destinatarioAdvogado);
        }

        return destinatarioAdvogados;
    }

    private LocalDateTime parseDateTime(String value) {
        try {
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            return null;
        }
    }
}
