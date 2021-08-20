package com.gft.desafiomvc.dto;

import javax.validation.constraints.NotEmpty;

import com.gft.desafiomvc.interfaces.SenhaValida;

public class UsuarioDTO {
	
	    @NotEmpty
	    private String nome;
	    @SenhaValida
	    private String password;
	    
	    
	    public UsuarioDTO() {			
		}
		
		public String getName() {
			return nome;
		}
		public void setName(String name) {
			this.nome = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}	    
}
