package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;

//CTRL + O - Importa todas bibliotecas de uma vez

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Schema(example = "email@email.com.br")
	@NotNull(message="Campo obrigatório!")
	@Email(message = "O usuário deve ser um email válido ex:maria@email.com")
	private String usuario;
	
	@NotNull
	private String senha;
	
	private String foto;

	@OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	
	
	//ordem dos atributos do construtor tem que ser a mesma ordem da model
		//construtor cheio
		public Usuario(Long id, String nome, String usuario, String senha, String foto) {
			this.id = id;
			this.nome = nome;
			this.usuario = usuario;
			this.senha = senha;
			this.foto = foto;
		}
		
		//construtor vazio
		public Usuario() {}


	
	//getters&setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}