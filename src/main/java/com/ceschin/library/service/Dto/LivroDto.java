package com.ceschin.library.service.Dto;

import java.util.UUID;

import com.ceschin.library.model.Livro;

public class LivroDto {
	
	private UUID id;
	private Long idEmprestimo;
	
	public LivroDto(){
		
	}

	public LivroDto(UUID id, Long idEmprestimo) {
		this.id = id;
		this.idEmprestimo = idEmprestimo;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Long idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	
	
	
	
}
