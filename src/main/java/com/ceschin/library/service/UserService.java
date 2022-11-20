package com.ceschin.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.User;
import com.ceschin.library.model.Emprestimo;
import com.ceschin.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User salvarUser(User user) {

		return userRepository.save(user);
	}

	public User salvarNovoUser(User user) {
		User userTemp = userRepository.findByUsername(user.getUsername()).get();
		if(userTemp == null){
			throw new RuntimeException("Já existe um usuário com esse username.");
		}

		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> listarUsers() {
		return userRepository.findAll();
	}

	public User listarUserById(UUID id) {
		Optional<User> userRecebido = userRepository.findById(id);
		if (userRecebido.isEmpty()) {
			throw new RuntimeException("Usuário não existe.");
		}

		return userRecebido.get();
	}

	public List<Emprestimo> listarEmprestimosCliente(UUID id) {
		User user = listarUserById(id);

		List<Emprestimo> listaEmprestimo = user.getEmprestimos();

		return listaEmprestimo;

	}

	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
