package com.ceschin.library.service.Dto;

import java.util.UUID;

import com.ceschin.library.model.Livro;

public class LivroDto {
	
	private UUID id;
	private UUID idEmprestimo;
	
	public LivroDto(){
		
	}

	public LivroDto(UUID id, UUID idEmprestimo) {
		this.id = id;
		this.idEmprestimo = idEmprestimo;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(UUID idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	
	
	
	
}
