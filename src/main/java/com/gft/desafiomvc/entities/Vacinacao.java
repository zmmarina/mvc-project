package com.gft.desafiomvc.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vacinacao {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message= "Uma data deve ser informada.")
	@FutureOrPresent(message="A data de vacinação deve ser futura.")
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "pessoa")
	private Pessoa pessoa;
	
	@OneToOne
	@JoinColumn(name = "lote_vacina")
	private LoteVacina loteVacina;
	
	@OneToOne
	@JoinColumn(name = "local_vacinacao")
	private LocalVacinacao localVacinacao;
	
	@NotEmpty(message= "A dose deve ser preenchida.")
	private String dose;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public LoteVacina getLoteVacina() {
		return loteVacina;
	}
	public void setLoteVacina(LoteVacina loteVacina) {
		this.loteVacina = loteVacina;
	}
	public LocalVacinacao getLocalVacinacao() {
		return localVacinacao;
	}
	public void setLocalVacinacao(LocalVacinacao localVacinacao) {
		this.localVacinacao = localVacinacao;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}

}
