package com.periferia.model.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.periferia.auth.TokenGenerator;
import com.periferia.model.dao.IClientesDao;
import com.periferia.model.entity.Cliente;
import com.periferia.model.service.IClienteService;
import com.periferia.web.dto.CountPromResponseDTO;
import com.periferia.web.dto.Payload;
import com.periferia.web.dto.User;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	private static final Logger LOGGER = Logger.getLogger(ClienteServiceImpl.class.getName()); 
	
	@Autowired
	IClientesDao dao;
	
	@Autowired
	private TokenGenerator tokenGenerator;

	@Override
	public List<Cliente> findAllByName() {		
		List<Cliente> lsClientes = new ArrayList();
		lsClientes.addAll(dao.findAllByOrderByNombreCompleto());		
		return lsClientes;
	}
	
	@Override
	public List<Cliente> findAllByAge() {
		List<Cliente> lsClientes = new ArrayList();
		lsClientes.addAll(dao.findAllByOrderByNacimiento());	
		
		return lsClientes;
	}

	@Override
	public List<CountPromResponseDTO> countAndProm() {
		List<CountPromResponseDTO> lsResponse = new ArrayList();
		int count = 0;
		count = (int) dao.count();
		CountPromResponseDTO countDto = new CountPromResponseDTO();
		countDto.setName("Total Clientes");
		countDto.setValue(""+count);		
		CountPromResponseDTO promDto = new CountPromResponseDTO();
		promDto.setName("Promedio de edad: ");
		List<Cliente> lsClientes = new ArrayList();
		lsClientes.addAll(dao.findAll());
		promDto.setValue(promediEdad(lsClientes));
		lsResponse = Arrays.asList(countDto, promDto);
		return lsResponse;
	}
	
	@SuppressWarnings("deprecation")
	public String promediEdad(List<Cliente> lsClientes) {
		String response = "";
		LocalDate hoy = LocalDate.now();   
		 
		List<Integer> lsEdades = new ArrayList();
		lsClientes.forEach((c) -> {
			LocalDate nacimiento = Instant.ofEpochMilli(c.getNacimiento().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			int edad = Period.between(nacimiento, hoy).getYears();
			lsEdades.add(new Integer(""+edad));			
		});		
		double promedio = lsEdades.stream().collect(Collectors.averagingDouble(x -> x));
		return ""+promedio;
	}	

	@Override
	public Cliente save(Cliente c) {
		return dao.save(c);
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> lsClientes = new ArrayList();
		//lsClientes.addAll(dao.findAll());
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

	@Override
	public User findByCorreo(String correo) {
		User response = new User();
		Cliente c =  dao.findByCorreo(correo);
		if (c != null) {
			ObjectMapper mapper = new ObjectMapper();		
			Payload payload = new Payload();
			payload.setCorreo(c.getCorreo());
			payload.setNombreCompleto(c.getNombreCompleto());
			String payloadJson ="";
			try {
				payloadJson = mapper.writeValueAsString(payload);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setToken(tokenGenerator.getJWTToken(payloadJson));
			response.setUser(correo);
		}
		return response;
	}

}
