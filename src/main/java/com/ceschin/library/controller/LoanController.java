package com.ceschin.library.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceschin.library.model.Loan;
import com.ceschin.library.service.LoanService;
import com.ceschin.library.service.Dto.BookDto;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping(value="/add-book")
	public Loan addBookOnLoan (@RequestBody BookDto book) {
					
		return loanService.addBook(book);
	}
	
	@PostMapping(value="/close-loan/{id}")
	public Loan closeLoan (@PathVariable(value="id") UUID id) {
					
		return loanService.closeLoan(id);
	}
	
	
}
