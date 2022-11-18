package com.ceschin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceschin.library.model.User;
import com.ceschin.library.model.Livro;
import com.ceschin.library.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService clienteService;
	
	@PostMapping("/novo-cliente")
	public User createUser(@RequestBody User user) {
		return clienteService.salvarNovoUser(user);
	}
}
