package com.lexon.Service.Test;

import com.lexon.model.CadastroPublicacao;

public interface ConsultaApiExterna {
    void consultar(CadastroPublicacao cadastro, String baseUrl);
}