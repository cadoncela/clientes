package com.periferia.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.periferia.model.service.impl.ClienteServiceImpl;
import com.periferia.web.dto.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	
	@Autowired
	private ClienteServiceImpl service;
	
	@PostMapping("user")
	public User login(@RequestParam("correo") String correo) {
		User user = new User();
		user = service.findByCorreo(correo);
		return user;
		
	}		
}
