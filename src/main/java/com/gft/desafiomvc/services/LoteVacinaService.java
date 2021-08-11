package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.LoteVacina;
import com.gft.desafiomvc.repositories.LoteVacinaRepository;

@Service
public class LoteVacinaService {
	
	@Autowired
	private LoteVacinaRepository loteVacinaRepository;
	
	public LoteVacina salvarLoteVacina(LoteVacina loteVacina) {
		
		return loteVacinaRepository.save(loteVacina);
	}
	
	public List<LoteVacina> listarLoteVacina(){
		
		return loteVacinaRepository.findAll();
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


}
