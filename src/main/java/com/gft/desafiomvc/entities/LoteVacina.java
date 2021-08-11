package com.gft.desafiomvc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	private String identificacaoLote;
	
	@NotNull(message= "A quantidade recebida deve ser informada.")
	private Integer quantidadeRecebida;
	
	@NotNull(message= "A quantidade restante deve ser informada.")
	private Integer quantidadeRestante;
	
	@NotNull(message= "A data de recebimento deve ser informada.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataRecebimento;
	
	@NotNull(message= "A data de validade deve ser informada.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataValidade;
	
	
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
	public Date getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
