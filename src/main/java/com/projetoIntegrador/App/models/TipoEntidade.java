package com.projetoIntegrador.App.models;

public enum TipoEntidade {
    ONG("ONG"), // 0
    EMPRESA("Empresa"), // 1
    ORGGOVERNAMENTAL("Órgão governamental"); // 2

    private String tipo;

    TipoEntidade(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
