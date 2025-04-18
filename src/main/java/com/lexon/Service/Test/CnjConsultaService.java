package com.lexon.Service.Test;

import com.lexon.model.CadastroPublicacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CnjConsultaService implements ConsultaApiExterna {

    @Override
    public void consultar(CadastroPublicacao cadastro, String baseUrl) {
        String url = String.format("%s?numeroOab=%s&ufOab=%s&nomeAdvogado=%s",
                baseUrl,
                cadastro.getInscricaoOab(),
                cadastro.getUfOab(),
                cadastro.getNomeAdvogado()
        );

        System.out.printf("[CNJ] Chamada: %s%n", url);
        // client.consultar(...);
    }
}
