package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.LocalVacinacao;
import com.gft.desafiomvc.repositories.LocalVacinacaoRepository;

@Service
public class LocalVacinacaoService {

	@Autowired
	private LocalVacinacaoRepository localVacinacaoRepository;
	
	public LocalVacinacao salvarLocalVacinacao(LocalVacinacao localVacinacao) {
		
		return localVacinacaoRepository.save(localVacinacao);
	}
	
	public List<LocalVacinacao> listarLocalVacinacao(){
		
		return localVacinacaoRepository.findAll();
	}
	
	public LocalVacinacao encontrarLocalVacinacaoa(Long id) throws Exception {
		
		Optional<LocalVacinacao> localVacinacao = localVacinacaoRepository.findById(id);
		
		if(localVacinacao.isEmpty()) {
			throw new Exception("Local de vacinação não encontrado!");
		}
		
		return localVacinacao.get();
	}
	
	public void excluirLocalVacinacao(Long id) {
		
		localVacinacaoRepository.deleteById(id);
	}	

}
