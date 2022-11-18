package com.ceschin.library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.User;
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
	@Autowired
	private UserService userService;

	public Emprestimo adicionarLivro(LivroDto livroDto) {
		Emprestimo emprestimo = new Emprestimo();

		try {
			Optional<Emprestimo> emprestimoBD = emprestimoRepository.findById(livroDto.getIdEmprestimo());
			if (emprestimoBD.isEmpty()) {
				emprestimo = criarEmprestimo(livroDto);
			} else {
				emprestimo = emprestimoBD.get();
			}
		} catch (Exception e) {
			System.out.println("erro gerado: " + e);
		}

		if (emprestimo.isAberto()) {
			List<Livro> livros = emprestimo.getLivros();
			if (livros == null) {
				livros = new ArrayList<>();
			}

			Optional<Livro> livroParaAdicionar = livroRepository.findById(livroDto.getId());
			Livro livro = livroParaAdicionar.get();
			livros.add(livro);

			emprestimo.setLivros(livros);

			return emprestimoRepository.save(emprestimo);
		} else {
			throw new RuntimeException("Esse empréstimo não pode ser alterado");
		}
	}

	public Emprestimo criarEmprestimo(LivroDto livroDto) {

		Emprestimo emprestimo = new Emprestimo();
		User user = userService.listarUserById();
		emprestimo.setUser(user);

		return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo getEmprestimoById(UUID id) {
		return emprestimoRepository.findById(id).get();
	}

	public Emprestimo fecharEmprestimo(UUID id) {
		Emprestimo emprestimo = emprestimoRepository.findById(id).get();
		if (!emprestimo.isAberto()) {
			throw new RuntimeException("Esse emprestimo não pode ser alterado");

		} else {
			List<Livro> listaLivros = emprestimo.getLivros();

			for (Livro livro : listaLivros) {
				UUID idLivro = livro.getId();
				Livro livroDB = livroRepository.findById(idLivro).get();

				int quantidade = livroDB.getEstoque().getQuantidade();

				livroDB.getEstoque().setQuantidade(quantidade - 1);
				livroRepository.save(livroDB);
			}

			emprestimo.setAberto(false);
			LocalDateTime dataCriada = LocalDateTime.now();
			LocalDateTime dataFinal = dataCriada.plusDays(7);
			emprestimo.setDataDeCriacao(dataCriada);
			emprestimo.setDataDeDevolucao(dataFinal);

			return emprestimoRepository.save(emprestimo);

		}

	}

}
