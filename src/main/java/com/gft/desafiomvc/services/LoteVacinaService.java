package com.gft.desafiomvc.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.LoteVacina;
import com.gft.desafiomvc.repositories.LoteVacinaRepository;


@Service
public class LoteVacinaService {
	
	@Autowired
	private LoteVacinaRepository loteVacinaRepository;
	

	public LoteVacina salvarLoteVacina(LoteVacina loteVacina) throws Exception {		
			
		if (loteVacina.getId() == null) {			
			if(loteVacinaRepository.encontrarLoteIdentificacao(loteVacina.getIdentificacaoLote()) == null) {
				return loteVacinaRepository.save(loteVacina);
			} else {
				throw new Exception("Lote de Vacina já cadastrado!");
			}				
		}else {
			if(loteVacinaRepository.encontrarLoteIdentificacao(loteVacina.getIdentificacaoLote()) != null) {
				return loteVacinaRepository.save(loteVacina);
			}
		}
				
		return loteVacinaRepository.save(loteVacina);
	}
	
	
	public List<LoteVacina> listarLoteVacina(){
		
		List<LoteVacina> todosLotes = loteVacinaRepository.findAll();
		
		LocalDate now = LocalDate.now();
		
		List<LoteVacina> lotesValidos = todosLotes.stream()
													.filter(i -> (i.getDataValidade().isAfter(now) || i.getDataValidade().isEqual(now) && i.getQuantidadeRestante() > 0))
													.collect(Collectors.toList());		
		
		return lotesValidos;
	}
	

	public LoteVacina encontrarloteVacina(Long id) throws Exception {
		
		Optional<LoteVacina> loteVacina = loteVacinaRepository.findById(id);
		
		if(loteVacina.isEmpty()) {
			throw new Exception("Lote de vacinas não encontrado!");
		}
		
		return loteVacina.get();
	}
	
	public void excluirLoteVacina(Long id) {
		
		loteVacinaRepository.deleteById(id);
	}
	
	public LoteVacina encontrarLoteIdentificacao(String identificacao) {
		
		return loteVacinaRepository.encontrarLoteIdentificacao(identificacao);
	}
	
	
}
