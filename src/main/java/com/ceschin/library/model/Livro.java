package com.ceschin.library.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private int paginas;
	@Column(nullable=false)
	private String Editora;
	@Column(nullable=false)
	private String genero;
	@OneToOne
	@Column(nullable=false)
	private Estoque estoque;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String getEditora() {
		return Editora;
	}
	public void setEditora(String editora) {
		Editora = editora;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Embedded
	public Estoque getEstoque() {
		return estoque;
	}
	
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	@Override
	public String toString() {
		return "Livro{ "
				+ "id = " + this.getId()
				+ "nome = " + this.getNome()
				+ "editora = " + this.getEditora()
				+ "estoque = " + this.getEstoque()
				+ "]";
	}
}
