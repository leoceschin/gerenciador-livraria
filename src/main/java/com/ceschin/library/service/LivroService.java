package com.ceschin.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Estoque;
import com.ceschin.library.model.Livro;
import com.ceschin.library.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro saveLivro(Livro livro){
		return livroRepository.save(livro);
	}
	
	public ResponseEntity<List<Livro>> getAllLivros(){
		return new ResponseEntity<List<Livro>>(livroRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Livro> getLivroById(UUID id) {
		Optional<Livro> livroRecebido = livroRepository.findById(id);
		return new ResponseEntity<Livro>(livroRecebido.get(), HttpStatus.OK);
		
	}
	
	public ResponseEntity<Livro> updateQuantidadeLivro(UUID id, Livro livro){
		return livroRepository.findById(id)
				.map(livroUpdate -> {
					livroUpdate.getEstoque().setQuantidade(livro.getEstoque().getQuantidade());
					Livro update = livroRepository.save(livroUpdate);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());

	}
	
	public ResponseEntity<Object> deleteLivro(UUID id){
		Optional<Livro> livroOptional = livroRepository.findById(id);
		livroRepository.delete(livroOptional.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
