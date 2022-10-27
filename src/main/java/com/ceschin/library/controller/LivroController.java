package com.ceschin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ceschin.library.model.Livro;
import com.ceschin.library.service.LivroService;

@Controller
@RequestMapping("/api/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@PostMapping("/novo-livro")
	public Livro postLivro(@RequestBody Livro livro) {
		return livroService.saveLivro(livro);
	}
	
}
