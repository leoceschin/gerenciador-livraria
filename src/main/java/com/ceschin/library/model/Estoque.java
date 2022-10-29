package com.ceschin.library.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Estoque implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int quantidade;
	private String localizacao;
	
	public Estoque() {}

	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
}
