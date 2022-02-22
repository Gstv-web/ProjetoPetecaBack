package com.projetoIntegrador.App.models;

public enum Demanda {

	OFERTA("Oferta"), 
	PPROCURA("Procura");
	
	private String demanda;

	Demanda(String demanda) {
		this.demanda = demanda;
	}

	public String getDemanda() {
		return demanda;
	}

}
