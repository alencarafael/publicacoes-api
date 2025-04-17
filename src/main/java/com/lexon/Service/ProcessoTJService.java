package com.lexon.Service;

import com.lexon.Dto.ProcessoTJResponseDTO;
import com.lexon.Util.HtmlParserTJUtil;
import com.lexon.Util.Tribunais;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessoTJService {

    public ProcessoTJResponseDTO buscarPrimeiroGrau(String numeroProcesso) {
        try {
            var info = HtmlParserTJUtil.extrairInfo(numeroProcesso);
            var dominio = Tribunais.getDominio(info.tribunal);
            return HtmlParserTJUtil.consultarPrimeiroGrau(info, dominio);
        } catch (Exception e) {
            return null;
        }
    }

    public ProcessoTJResponseDTO buscarSegundoGrau(String numeroProcesso) {
        try {
            var info = HtmlParserTJUtil.extrairInfo(numeroProcesso);
            var dominio = Tribunais.getDominio(info.tribunal);
            return HtmlParserTJUtil.consultarSegundoGrau(info, dominio);
        } catch (Exception e) {
            return null;
        }
    }
}
