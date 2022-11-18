package com.ceschin.library.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_emprestimos")
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@ManyToOne
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Livro> livros;
	
	private LocalDateTime dataDeCriacao;
	
	private LocalDateTime dataDeDevolucao;
	
	private boolean aberto = true;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public LocalDateTime getDataDeDevolucao() {
		return dataDeDevolucao;
	}
	public void setDataDeDevolucao(LocalDateTime dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}
	public boolean isAberto() {
		return aberto;
	}
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	
	
	
}

