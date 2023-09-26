package com.periferia.web.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountPromResponseDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String value;

}
