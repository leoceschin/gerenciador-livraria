package com.ceschin.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Book;
import com.ceschin.library.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book saveBook(Book book){
		return bookRepository.save(book);
	}
	
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Book> getBookById(UUID id) {
		Optional<Book> bookOptional = bookRepository.findById(id);
		return new ResponseEntity<Book>(bookOptional.get(), HttpStatus.OK);
		
	}
	
	public ResponseEntity<Book> updateBookInventoryQuantity(UUID id, Book book){
		return bookRepository.findById(id)
				.map(bookUpdate -> {
					bookUpdate.getInventory().setQuantity(book.getInventory().getQuantity());
					Book update = bookRepository.save(bookUpdate);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());

	}
	
	public ResponseEntity<Object> deleteBook(UUID id){
		Optional<Book> bookOptional = bookRepository.findById(id);
		bookRepository.delete(bookOptional.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
