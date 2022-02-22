package com.projetoIntegrador.App.DTO;

public class CredentialsDTO {
    
    private long userId;
    private String email;
    private String senha;
    private String foto;
    private String token;



    public CredentialsDTO(long userId, String email, String senha, String foto, String token) {
        this.userId = userId;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.token = token;
    }


    public CredentialsDTO() {
    }



    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
