package com.gft.desafiomvc.controllers;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

import com.gft.desafiomvc.entities.LoteVacina;

public class RelatorioVacinaDTO {
	
	private Long id;
	private String vacina;
	private String identificacaoLote;
	private Integer quantidadeRestante;
	private LocalDate dataValidade;
	private Long diasVencimento;
	
	public RelatorioVacinaDTO() {}
	
	public RelatorioVacinaDTO(LoteVacina loteVacina) {
		this.id = loteVacina.getId();
		this.vacina = loteVacina.getVacina().getNome();
		this.identificacaoLote = loteVacina.getIdentificacaoLote();
		this.quantidadeRestante = loteVacina.getQuantidadeRestante();
		this.dataValidade = loteVacina.getDataValidade();
		this.diasVencimento = DAYS.between(LocalDate.now(), dataValidade);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVacina() {
		return vacina;
	}

	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	public String getIdentificacaoLote() {
		return identificacaoLote;
	}

	public void setIdentificacaoLote(String identificacaoLote) {
		this.identificacaoLote = identificacaoLote;
	}

	public Integer getQuantidadeRestante() {
		return quantidadeRestante;
	}

	public void setQuantidadeRestante(Integer quantidadeRestante) {
		this.quantidadeRestante = quantidadeRestante;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Long getDiasVencimento() {
		return diasVencimento;
	}

	public void setDiasVencimento(Long diasVencimento) {
		this.diasVencimento = diasVencimento;
	}

	
}
