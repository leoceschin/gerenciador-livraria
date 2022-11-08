package com.ceschin.library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Emprestimo;
import com.ceschin.library.model.Livro;
import com.ceschin.library.repository.EmprestimoRepository;
import com.ceschin.library.repository.LivroRepository;
import com.ceschin.library.service.Dto.LivroDto;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	public Emprestimo criarEmprestimo(LivroDto livroDto) {
		Livro livroParaAdicionar = livroRepository.findById(livroDto.getId()).get();
		LocalDateTime dataCriada = LocalDateTime.now();
		LocalDateTime dataFinal = dataCriada.plusDays(7);
		
		Emprestimo emprestimo = new Emprestimo();
		
		emprestimo.setDataDeCriacao(dataCriada);
		emprestimo.setDataDeDevolucao(dataFinal);
		List<Livro> listaLivros = new ArrayList<>();
		listaLivros.add(livroParaAdicionar);
		
		emprestimo.setLivros(listaLivros);
		
		return emprestimoRepository.save(emprestimo);
	}
	
	
	
	public Emprestimo getEmprestimoById(Long id) {
		return emprestimoRepository.findById(id).get();
	}
	
//	public List<Livro> getLivrosDoEmprestimo(){
//		
//	}

}
