package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.Vacina;
import com.gft.desafiomvc.repositories.VacinaRepository;


@Service
public class VacinaService {
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	public Vacina salvarVacina(Vacina vacina) {
		
		return vacinaRepository.save(vacina);
	}
	
	public List<Vacina> listarVacinas(){
		
		return vacinaRepository.findAll();
	}
	
	public Vacina encontrarVacina(Long id) throws Exception {
		
		Optional<Vacina> vacina = vacinaRepository.findById(id);
		
		if(vacina.isEmpty()) {
			throw new Exception("Vacina não encontrada!");
		}
		
		return vacina.get();
	}
	
	public void excluirVacina(Long id) {
		
		vacinaRepository.deleteById(id);
	}	

}
