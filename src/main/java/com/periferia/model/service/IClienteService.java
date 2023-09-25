package com.periferia.model.service;

import java.util.List;

import com.periferia.model.entity.Cliente;

public interface IClienteService {
	
	List<Cliente> findAll();
	
	List<Cliente> findAllByName();
	
	List<Cliente> findAllByAge();
	
	List<String> countAndProm();
	
	Cliente save(Cliente c);

}
