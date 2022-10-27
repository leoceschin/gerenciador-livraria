package com.ceschin.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Livro;
import com.ceschin.library.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro saveLivro(Livro livro){
		return livroRepository.save(livro);
	}
	
}
