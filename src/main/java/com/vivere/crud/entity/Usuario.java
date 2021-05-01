package com.vivere.crud.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCadastro;

	@Column(nullable = false, length = 30)
	@JsonProperty("nome")
	private String nome;

	@Column(nullable = false, length = 15, unique = true)
	@JsonProperty("login")
	private String login;

	@Column(nullable = false, length = 10)
	private String senha;

	@Column(nullable = false, length = 11)
	private String telefone;

	@Column(nullable = true, length = 100)
	@JsonProperty("email")
	private String email;

	@Column(nullable = false, length = 1)
	private String perfil;

	@Column(name = "status", nullable = true, length = 1)
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
	public Usuario() {
	}

	public Usuario(LocalDateTime dataCadastro, String nome, String login, String senha, String telefone, String email,
			String perfil, String status) {
		super();
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
		this.perfil = perfil;
		this.status = status;
	}

}