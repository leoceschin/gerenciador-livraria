package com.ceschin.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Cliente;
import com.ceschin.library.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvarCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente listarClienteById(Long id) {
		Optional<Cliente> clienteRecebido = clienteRepository.findById(id);
		return clienteRecebido.get();
	}
	
}
