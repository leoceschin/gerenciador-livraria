package com.ceschin.library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.User;
import com.ceschin.library.model.Loan;
import com.ceschin.library.model.Book;
import com.ceschin.library.repository.LoanRepository;
import com.ceschin.library.repository.BookRepository;
import com.ceschin.library.repository.UserRepository;
import com.ceschin.library.service.Dto.BookDto;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;

	public Loan addBook (BookDto bookDto) {
		Loan loan = new Loan();

		try {
			Optional<Loan> loanBD = loanRepository.findById(bookDto.getIdLoan());
			if (loanBD.isEmpty()) {
				loan = createLoan(bookDto);
			} else {
				loan = loanBD.get();
			}
		} catch (Exception e) {
			System.out.println("erro gerado: " + e);
		}

		if (loan.isOpen()) {
			List<Book> books = loan.getBooks();
			if (books == null) {
				books = new ArrayList<>();
			}

			Optional<Book> livroParaAdicionar = bookRepository.findById(bookDto.getId());
			Book livro = livroParaAdicionar.get();
			books.add(livro);

			loan.setBooks(books);

			return loanRepository.save(loan);
		} else {
			throw new RuntimeException("Esse empréstimo não pode ser alterado");
		}
	}

	public Loan createLoan(BookDto livroDto) {

		Loan loan = new Loan();

		User user = userRepository.findByUsername(getCurrentLoggedUsername()).get();
		loan.setUser(user);

		return loanRepository.save(loan);
	}

	public String getCurrentLoggedUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof User) {
			username = ((User) principal).getUsername().toString();
		} else {
			username = principal.toString();
		}

		return username;
	}

	public Loan getLoanById(UUID id) {
		return loanRepository.findById(id).get();
	}

	public Loan closeLoan(UUID id) {
		Loan loan = loanRepository.findById(id).get();
		if (!loan.isOpen()) {
			throw new RuntimeException("Esse empréstimo não pode ser alterado");

		} else {
			List<Book> bookList = loan.getBooks();

			for (Book book : bookList) {
				UUID idBook = book.getId();
				Book bookDB = bookRepository.findById(idBook).get();

				int quantity = bookDB.getInventory().getQuantity();

				bookDB.getInventory().setQuantity(quantity - 1);
				bookRepository.save(bookDB);
			}

			loan.setOpen(false);
			LocalDateTime createdAt = LocalDateTime.now();
			LocalDateTime closedAt = createdAt.plusDays(7);
			loan.setCreatedAt(createdAt);
			loan.setClosedAt(closedAt);

			return loanRepository.save(loan);

		}

	}

}