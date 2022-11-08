package com.ceschin.library.service.Dto;

import java.util.UUID;

import com.ceschin.library.model.Livro;

public class LivroDto {
	
	private UUID id;
	private int quantidade;
	
	public LivroDto(){
		
	}

	public LivroDto(UUID id, int quantidade) {
		this.id = id;
		this.quantidade = quantidade;
	}
	
	public LivroDto(Livro livro) {
		id = livro.getId();
		quantidade = livro.getEstoque().getQuantidade();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
