package com.projetoIntegrador.App.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long UserId;
	
	@NotBlank
	private String razaoSocial;
	
	@Schema(example = "email@email.com.br")
	@NotNull(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email válido!")
	private String email;
	
	@NotBlank
	@Size(min = 5)
	private String senha;
	
	@NotNull
	private TipoEntidade tipo;

	@NotBlank
	private String endereco;
	
	@NotBlank
	private String contato;

	private String foto;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Postagem> postagem;
	
	

	public User(long UserId, String razaoSocial, String email, String senha, TipoEntidade tipo, String endereco, String contato, String foto, List<Postagem> postagem) {
		this.UserId = UserId;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.endereco = endereco;
		this.contato = contato;
		this.foto = foto;
		this.postagem = postagem;
	}
	
	
	public User() {};
	

	public long getUserId() {
		return this.UserId;
	}

	public void setUserId(long UserId) {
		this.UserId = UserId;
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

	public TipoEntidade getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoEntidade tipo) {
		this.tipo = tipo;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return this.contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return this.postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
