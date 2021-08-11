package com.gft.desafiomvc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Vacina {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message= "O nome deve ser preenchido.")
	private String nome;
	
	@NotEmpty(message= "O laboratório deve ser informado.")
	private String laboratorio;
	
	
	@NotNull(message= "A posologia deve ser informada.")
	private Boolean posologia;
	
	
	@NotNull(message= "O intervalo entre as doses deve ser informado.")
	private Integer intervaloDoses;
	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public Boolean getPosologia() {
		return posologia;
	}
	public void setPosologia(Boolean posologia) {
		this.posologia = posologia;
	}
	public Integer getIntervaloDoses() {
		return intervaloDoses;
	}
	public void setIntervaloDoses(Integer intervaloDoses) {
		this.intervaloDoses = intervaloDoses;
	}

}
