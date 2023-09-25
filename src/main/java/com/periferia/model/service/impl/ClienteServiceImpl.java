package com.periferia.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.periferia.controller.ClienteController;
import com.periferia.model.dao.IClientesDao;
import com.periferia.model.entity.Cliente;
import com.periferia.model.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	private static final Logger LOGGER = Logger.getLogger(ClienteServiceImpl.class.getName()); 
	
	@Autowired
	IClientesDao dao;

	@Override
	public List<Cliente> findAllByName() {		
		return dao.findAllByOrderByNombreCompleto();
	}

	@Override
	public List<Cliente> findAllByAge() {
		return dao.findAllByOrderByNacimiento();
	}

	@Override
	public List<String> countAndProm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente save(Cliente c) {
		return dao.save(c);
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> lsClientes = new ArrayList();
		lsClientes.addAll(dao.findAll());
		Cliente c = lsClientes.get(0);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(c);
			LOGGER.log(Level.INFO, "[clientes].[ClienteServiceImpl.findAll]. Cliente: {0} ", json);		
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lsClientes;
	}

}
