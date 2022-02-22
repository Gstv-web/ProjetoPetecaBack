package com.projetoIntegrador.App.models;

public enum Categoria {
    DOACAODEROUPA("Doação de roupa"), 
    DOACAODEALIMENTOS("Doação de alimentos"), 
    VAGACURSOLIVRE("Vaga de curso livre"), 
    VAGACURSOPROFISSIONALIZANTE("Vaga de curso profissionalizante"), 
    VAGACONTRATURNO("Vaga de contra turno"), 
    VAGAOFICINAS("Vaga oficinas"), 
    VAGASOCIAL("Vaga social");
    
    private String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
}
