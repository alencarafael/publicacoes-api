package com.lexon.Util;

import java.util.HashMap;
import java.util.Map;

public enum Tribunais {
    TJAL("02", "www2.tjal.jus.br"),
    TJCE("06", "esaj.tjce.jus.br"),
    TJSP("26", "esaj.tjsp.jus.br");

    private final String codigo;
    private final String dominio;

    Tribunais(String codigo, String dominio) {
        this.codigo = codigo;
        this.dominio = dominio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDominio() {
        return dominio;
    }

    private static final Map<String, String> mapaCodigoDominio = new HashMap<>();

    static {
        for (Tribunais t : values()) {
            mapaCodigoDominio.put(t.getCodigo(), t.getDominio());
        }
    }

    public static String getDominio(String codigoTribunal) {
        return mapaCodigoDominio.getOrDefault(codigoTribunal, "");
    }
}
