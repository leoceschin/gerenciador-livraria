package com.ceschin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ceschin.library.model.Loan;
import com.ceschin.library.service.LoanService;
import com.ceschin.library.service.Dto.BookDto;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@PostMapping(value = "/add-book-loan")
	public Loan addBookOnLoan(@RequestBody BookDto book) {

		return loanService.addBookOnLoan(book);
	}

	@PostMapping(value = "/close-loan")
	public Loan closeLoan() {

		return loanService.closeLoan();
	}

	@PostMapping(value = "/delete-book-loan")
	public Loan deleteBookFromLoan(@RequestBody BookDto book) {
		return loanService.deleteBookFromLoan(book);
	}

}
