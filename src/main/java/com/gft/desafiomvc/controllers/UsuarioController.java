package com.gft.desafiomvc.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gft.desafiomvc.dto.UsuarioDTO;

@Controller
@RequestMapping("/login")
public class UsuarioController {
	
	@ModelAttribute("usuario")
	public UsuarioDTO usuarioDTO() {
		return new UsuarioDTO();		
	}
	
	@GetMapping
	public String mostrarForm() {
		return "login";
	}
	
	@PostMapping 
	public String enviarForm(@Valid @ModelAttribute("usuario") UsuarioDTO usuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		return "sucesso";
	}

}
