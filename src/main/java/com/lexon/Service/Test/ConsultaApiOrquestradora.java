package com.lexon.Service.Test;

import com.lexon.model.AgenciaTribunal;
import com.lexon.model.AgenciaTribunalApi;
import com.lexon.model.CadastroPublicacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ConsultaApiOrquestradora {

    @Inject
    CnjConsultaService cnjService;
    @Inject
    TjspConsultaService tjspService;

    public void processarCadastro(CadastroPublicacao cadastro) {
        List<String> siglas = List.of(cadastro.getSiglaAgenciaTribunal().split(","));

        for (String sigla : siglas) {
            AgenciaTribunal tribunal = AgenciaTribunal.find("sigla", sigla).firstResult();
            if (tribunal == null) continue;

            AgenciaTribunalApi api = AgenciaTribunalApi.find("uf = ?1 and agenciaTribunal.id = ?2",
                    cadastro.getUfOab(), tribunal.getId()).firstResult();

            if (api == null || api.getUrlApi() == null) continue;

            switch (sigla) {
                case "CNJ" -> cnjService.consultar(cadastro, api.getUrlApi());
                case "TJSP" -> tjspService.consultar(cadastro, api.getUrlApi());
                // outros tribunais...
            }
        }
    }
}