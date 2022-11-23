package com.ceschin.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ceschin.library.model.Book;
import com.ceschin.library.service.BookService;

@Controller
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/new-book")
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@GetMapping("/collection")
	public ResponseEntity<List<Book>> getBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable (value="id") UUID id) {
		return bookService.getBookById(id);
	}
	
	@PatchMapping("/update-inventory-quantity/{id}")
	public ResponseEntity<Book> updateInventoryQuantity(@PathVariable (value="id") UUID id, @RequestBody Book book){
		return bookService.updateBookInventoryQuantity(id, book);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value="id") UUID id){
		return bookService.deleteBook(id);
	}
		
}
