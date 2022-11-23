package com.ceschin.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.User;
import com.ceschin.library.model.Loan;
import com.ceschin.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {

		return userRepository.save(user);
	}

	public User saveNewUser(User user) {
		User userTemp = userRepository.findByUsername(user.getUsername()).get();
		if (userTemp == null) {
			throw new RuntimeException("Já existe um usuário com esse username.");
		}

		
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(UUID id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("Usuário não existe.");
		}

		return userOptional.get();
	}

	public List<Loan> getLoansOfUser(UUID id) {
		User user = getUserById(id);

		List<Loan> loanList = user.getLoans();

		return loanList;

	}

	

}
