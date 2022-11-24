package com.ceschin.library.service.Dto;

import java.util.UUID;

public class BookDto {
	
	private UUID id;
	//private UUID idLoan;
	
	public BookDto(){
		
	}

	public BookDto(UUID id) {
		this.id = id;
		
	}

	/* public BookDto(UUID id, UUID idLoan) {
		this.id = id;
		this.idLoan = idLoan;
	} */
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	/* public UUID getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(UUID idLoan) {
		this.idLoan = idLoan;
	} */

	
	
	
	
}
