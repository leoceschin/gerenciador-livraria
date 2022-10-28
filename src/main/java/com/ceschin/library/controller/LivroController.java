package com.ceschin.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ceschin.library.model.Livro;
import com.ceschin.library.service.LivroService;

@Controller
@RequestMapping("/api/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@PostMapping("/novo-livro")
	public Livro createLivro(@RequestBody Livro livro) {
		return livroService.saveLivro(livro);
	}
	
	@GetMapping
	public ResponseEntity<List<Livro>> getLivros(){
		return livroService.getAllLivros();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable (value="id") UUID id) {
		return livroService.getLivroById(id);
	}
		
}
