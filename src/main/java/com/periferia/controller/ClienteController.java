package com.periferia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.periferia.model.entity.Cliente;
import com.periferia.model.service.impl.ClienteServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteServiceImpl service;
	
private static final Logger LOGGER = Logger.getLogger(ClienteController.class.getName()); 
	
	@GetMapping("/sumar/{param}/{param2}")
	public ResponseEntity<Integer> sumarNumerosGet(@PathVariable int param, @PathVariable int param2) {
		LOGGER.log(Level.INFO, "[clientes].[ClienteController.sumarNumerosGet]. Entarndo a sumar ClienteController. {0} ", param + " - " + param2);		
		int sum = param+param2;
		return new ResponseEntity<Integer>(sum, HttpStatus.OK);
	}
	
	@GetMapping("/clientes")
    public List<Cliente> findAll() {
		LOGGER.log(Level.INFO, "[clientes].[ClienteController.findAll]. Entarndo a findAll ClienteController. {0} ");	
        List <Cliente> lsClientes = new ArrayList<>();
        lsClientes.addAll(service.findAll());                
        return lsClientes;
    }
	
	@PostMapping("/clientes")
	public Cliente save(@RequestBody Cliente c) {
		return service.save(c);
	}

}
