package com.projetoIntegrador.App.DTO;

public class UserLoginDTO {
	
	private String email;
	private String senha;


	public UserLoginDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	
	public UserLoginDTO() {
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
	

}
