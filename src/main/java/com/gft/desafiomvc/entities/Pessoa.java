package com.gft.desafiomvc.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
		
	@CPF(message= "CPF inválido.")
	@Column(unique= true, nullable = false)
	private String cpf;
	
	@NotEmpty(message= "O nome deve ser preenchido.")
	@Size(min=4, max=100, message = "O nome deve conter entre 4 e 100 caracteres.")
	private String nome;
	
	@NotNull(message= "A data de nascimento deve ser informada.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "A data deve estar no passado.")
	private LocalDate nascimento;
	
	@Embedded
	private Endereco endereco;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
		

}
