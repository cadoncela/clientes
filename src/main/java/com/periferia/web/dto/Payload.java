package com.periferia.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payload {
	
	@JsonProperty("nombre")
	private String nombreCompleto;
	
	private String correo;

}
