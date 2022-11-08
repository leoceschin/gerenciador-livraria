package com.ceschin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceschin.library.model.Cliente;
import com.ceschin.library.model.Livro;
import com.ceschin.library.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/novo-cliente")
	public Cliente criaCLiente(@RequestBody Cliente cliente) {
		return clienteService.salvarCliente(cliente);
	}
}
