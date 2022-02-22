package com.projetoIntegrador.App.DTO;

public class UserLoginDTO {
	
	private String razaoSocial;
	private String email;
	private String senha;
	private String token;


	public UserLoginDTO(String razaoSocial, String email, String senha, String token) {
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.senha = senha;
		this.token = token;
	}

	
	public UserLoginDTO() {
	}
	

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
