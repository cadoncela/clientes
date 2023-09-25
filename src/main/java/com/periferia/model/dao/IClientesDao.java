package com.periferia.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.periferia.model.entity.Cliente;

public interface IClientesDao extends JpaRepository<Cliente, Long> {
	
	public List<Cliente> findAllByOrderByNombreCompleto();
	
	public List<Cliente> findAllByOrderByNacimiento();

}
