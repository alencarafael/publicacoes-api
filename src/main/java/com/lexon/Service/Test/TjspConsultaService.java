package com.lexon.Service.Test;

import com.lexon.model.CadastroPublicacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TjspConsultaService implements ConsultaApiExterna {

    @Override
    public void consultar(CadastroPublicacao cadastro, String baseUrl) {
        String numOabConcat = cadastro.getInscricaoOab() + cadastro.getUfOab();
        String url = String.format("%s?cbPesquisa=NUMOAB&numOAB=%s", baseUrl, numOabConcat);

        System.out.printf("[TJSP] Chamada: %s%n", url);
        // futura lógica de scraping ou parsing da resposta
    }
}
