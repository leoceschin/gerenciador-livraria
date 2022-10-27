package com.ceschin.library.model;

import javax.persistence.Embeddable;

@Embeddable
public class Estoque {
	
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
