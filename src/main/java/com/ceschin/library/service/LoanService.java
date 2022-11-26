package com.ceschin.library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	public Loan addBookOnLoan(BookDto bookDto) {
		Loan loan = getActiveLoan();

		List<Book> books = loan.getBooks();
		if (books == null) {
			books = new ArrayList<>();
		}

		Optional<Book> bookToAdd = bookRepository.findById(bookDto.getId());
		Book book = bookToAdd.get();
		books.add(book);

		loan.setBooks(books);

		return loanRepository.save(loan);

	}

	private Loan getActiveLoan() {
		User user = getActiveUser();
		List<Loan> loanList = user.getLoans();
		Loan loan = new Loan();

		if (loanList != null) {
			boolean loanHasActivated = false;
			for (Loan loanTemp : loanList) {
				if (loanTemp.isOpen()) {
					loanHasActivated = true;
					loan = loanTemp;
				}

			}
			if (!loanHasActivated) {
				loan = createLoan(user);
			}

		} else {
			loan = createLoan(user);

		}

		return loan;
	}

	public Loan createLoan(User user) {
		Loan loan = new Loan();
		loan.setUser(user);
		return loanRepository.save(loan);
	}

	public String getCurrentLoggedUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}

	public User getActiveUser() {
		User user = userRepository.findByUsername(getCurrentLoggedUsername()).get();
		return user;
	}

	public Optional<Loan> getLoanById(UUID id) {
		return loanRepository.findById(id);
	}

	public Loan deleteBookFromLoan(BookDto bookDto) {
		Loan loan = getActiveLoan();
		List<Book> loanBookList = loan.getBooks();

		int bookIndex = -1;
		for (Book book : loanBookList) {
			boolean compare = (book.getId()).equals(bookDto.getId());

			if (compare) {
				bookIndex = loanBookList.indexOf(book);

			}
		}
		if (bookIndex != -1)
			loanBookList.remove(bookIndex);
		loan.setBooks(loanBookList);
		return loanRepository.save(loan);
	}

	public Loan closeLoan() {
		Loan loan = getActiveLoan();
		List<Book> bookList = loan.getBooks();

		if (bookList != null) {
			for (Book book : bookList) {
				UUID idBook = book.getId();
				Book bookDB = bookRepository.findById(idBook).get();

				int quantity = bookDB.getInventory().getQuantity();

				bookDB.getInventory().setQuantity(quantity - 1);
				bookRepository.save(bookDB);
			}
		} else {
			throw new RuntimeException("Um empréstimo vazio não pode ser fechado! Adicione um livro");
		}

		loan.setOpen(false);
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime closedAt = createdAt.plusDays(7);
		loan.setCreatedAt(createdAt);
		loan.setClosedAt(closedAt);
		loan.setBooks(bookList);

		return loanRepository.save(loan);

	}

}
