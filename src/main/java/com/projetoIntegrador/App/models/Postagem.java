package com.projetoIntegrador.App.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 5, max = 40)
	private String titulo;
	
	@NotBlank
	@Size(min = 5, max = 500)
	private String descricao;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String localidade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Categoria tipoPostagem; // tipo de postagem (doação roupa, doação de alimento, vaga de curso livre, vaga de curso profissionalizante)
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Demanda demanda;

	private String visualizacao;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private User user;		

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Categoria getTipoPostagem() {
		return this.tipoPostagem;
	}

	public void setTipoPostagem(Categoria tipoPostagem) {
		this.tipoPostagem = tipoPostagem;
	}

	public Demanda getDemanda() {
		return this.demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	public String getVisualizacao() {
		return this.visualizacao;
	}

	public void setVisualizacao(String visualizacao) {
		this.visualizacao = visualizacao;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
		
	
}
