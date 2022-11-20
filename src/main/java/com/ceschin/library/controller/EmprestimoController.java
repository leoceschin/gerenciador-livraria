package com.ceschin.library.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceschin.library.model.Emprestimo;
import com.ceschin.library.service.EmprestimoService;
import com.ceschin.library.service.Dto.LivroDto;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@PostMapping(value="/adiconar-livro")
	public Emprestimo adicionarLivro(@RequestBody LivroDto livro) {
					
		return emprestimoService.adicionarLivro(livro);
	}
	
	@PostMapping(value="/fechar-emprestimo/{id}")
	public Emprestimo adicionarLivro(@PathVariable(value="id") UUID id) {
					
		return emprestimoService.fecharEmprestimo(id);
	}
	
	
}
