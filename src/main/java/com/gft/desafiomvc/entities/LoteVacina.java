package com.gft.desafiomvc.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LoteVacina {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "vacina")
	private Vacina vacina;
	
	@NotEmpty(message= "O lote deve ser informado.")
	@Column(unique= true, nullable = false)
	private String identificacaoLote;
	
	@NotNull(message= "A quantidade recebida deve ser informada.")
	@Min(1)
	private Integer quantidadeRecebida;
	
	@NotNull(message= "A quantidade restante deve ser informada.")
	private Integer quantidadeRestante;
	
	@NotNull(message= "A data de recebimento deve ser informada.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@PastOrPresent(message="A data de recebimento deve ser a de hoje ou anterior.")
	private LocalDate dataRecebimento;
	
	@NotNull(message= "A data de validade deve ser informada.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message="A data de validade deve ser futura.")
	private LocalDate dataValidade;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Vacina getVacina() {
		return vacina;
	}
	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	public String getIdentificacaoLote() {
		return identificacaoLote;
	}
	public void setIdentificacaoLote(String identificacaoLote) {
		this.identificacaoLote = identificacaoLote;
	}
	public Integer getQuantidadeRecebida() {
		return quantidadeRecebida;
	}
	public void setQuantidadeRecebida(Integer quantidadeRecebida) {
		this.quantidadeRecebida = quantidadeRecebida;
	}
	public Integer getQuantidadeRestante() {
		return quantidadeRestante;
	}
	public void setQuantidadeRestante(Integer quantidadeRestante) {
		this.quantidadeRestante = quantidadeRestante;
	}
	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
