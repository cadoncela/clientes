package com.periferia.model.service;

import java.util.List;

import com.periferia.model.entity.Cliente;
import com.periferia.web.dto.CountPromResponseDTO;
import com.periferia.web.dto.User;

public interface IClienteService {
	
	List<Cliente> findAll();
	
	List<Cliente> findAllByName();
	
	List<Cliente> findAllByAge();
	
	List<CountPromResponseDTO> countAndProm();
	
	Cliente save(Cliente c);
	
	User findByCorreo(String correo);

}
