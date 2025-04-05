package com.lexon.Client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/api/v1")
@RegisterRestClient(configKey = "deoab-api")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface DeoabClient {

    @GET
    @Path("/diario/pesquisa")
    PublicacaoResponse pesquisarPublicacoes(
            @QueryParam("pagina") @DefaultValue("1") int pagina,
            @QueryParam("termo") String termo,
            @QueryParam("idOrganizacao") int idOrganizacao,
            @QueryParam("inicio") String inicio,
            @QueryParam("fim") String fim
    );

    @GET
    @Path("/publicacao/{id}/publicado")
    DetalhePublicacaoResponse buscarDetalhePublicacao(@PathParam("id") int id);

    // --- DTOs internos para resposta da API ---

    class PublicacaoResponse {
        public int pagina;
        public int quantidade;
        public int quantidadeEdicoesExtras;
        public List<PublicacaoResumo> itens;
    }

    class PublicacaoResumo {
        public int id;
        public String conteudo;
        public String dataPublicacao;
        public int edicao;
        public int edicaoExtra;
        public int pagina;
    }

    class DetalhePublicacaoResponse {
        public int id;
        public String ano;
        public String issn;
        public String titulo;
        public String conteudo;
        public String conteudoCodificado;
        public TipoPublicacao tipoPublicacao;
        public SetorPublicacao setorPublicacao;
        public String local;
        public String dataPublicacao;
        public int pagina;
        public int edicao;
        public String edicaoExtra;
    }

    class TipoPublicacao {
        public int id;
        public String descricao;
        public int ordem;
    }

    class SetorPublicacao {
        public Organizacao organizacao;
        public int organizacaoId;
        public int setor;
        public String nomeAlternativo;
        public String nomeOriginal;
        public int ordem;
    }

    class Organizacao {
        public int id;
        public int idOrganizacao;
        public String nomeAlternativo;
        public String ordem;
        public String pagina;
        public String local;
    }
}
