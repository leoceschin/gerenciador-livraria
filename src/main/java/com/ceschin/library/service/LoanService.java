package com.ceschin.library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

		for (Loan loanTemp : loanList) {
			if (loanTemp.isOpen()) {
				loan = loanTemp;
			}
		}
		return loan;
	}

	public Loan createLoan() {

		Loan loan = new Loan();

		User user = userRepository.findByUsername(getCurrentLoggedUsername()).get();
		UUID idUser = user.getId();
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
		Loan loanTemp = new Loan();
		List<Loan> loans = user.getLoans();
		if (!loans.isEmpty()) {
			for (Loan loan : loans) {
				if (loan.isOpen()) {
					loanTemp = loan;
				}
			}
		} else {
			loanTemp = createLoan();

		}
		loans.add(loanTemp);
		user.setLoans(loans);
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
		if(bookIndex != -1) 
			loanBookList.remove(bookIndex);
		loan.setBooks(loanBookList);
		return loanRepository.save(loan);
	}

	public Loan closeLoan(UUID id) {
		Loan loan = getActiveLoan();
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
