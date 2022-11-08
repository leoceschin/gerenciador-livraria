package com.ceschin.library.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_emprestimos")
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany
	private List<Livro> livros;
	
	@Column
	private LocalDateTime dataDeCriacao;
	
	private LocalDateTime dataDeDevolucao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	
	
	
}

