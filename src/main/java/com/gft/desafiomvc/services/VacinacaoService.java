package com.gft.desafiomvc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.LoteVacina;
import com.gft.desafiomvc.entities.Vacinacao;
import com.gft.desafiomvc.repositories.LoteVacinaRepository;
import com.gft.desafiomvc.repositories.VacinacaoRepository;

@Service
public class VacinacaoService {

	@Autowired
	private VacinacaoRepository vacinacaoRepository;
	
	@Autowired
	private LoteVacinaRepository loteVacinaRepository;
	
	public Vacinacao salvarVacinacao(Vacinacao vacinacao) {
		
		return vacinacaoRepository.save(vacinacao);
	}
	
	public List<Vacinacao> listarVacinacao(){
		
		return vacinacaoRepository.findAll();
	}
	
	public Vacinacao encontrarVacinacao(Long id) throws Exception {
		
		Optional<Vacinacao> vacinacao = vacinacaoRepository.findById(id);
		
		if(vacinacao.isEmpty()) {
			throw new Exception("Vacinação não encontrada!");
		}
		
		return vacinacao.get();
	}
	
	public void excluirVacinacao(Long id) {
		
		vacinacaoRepository.deleteById(id);
	}	
	
	public List<Vacinacao> listarVacinacaoPessoaId(String cpf){
		
		return vacinacaoRepository.encontrarVacinacaoPacienteId(cpf);
	}
	
	public List<Vacinacao> listarPacientesDose(String dose){
	
		return vacinacaoRepository.encontrarPacienteDose(dose);
	}
	
	public List<LoteVacina> listarLotesMesmaVacina(Long id){
		
		List<LoteVacina> lotesMesmaVacina = new ArrayList<>();
		
		try {
			Vacinacao vacinacao = encontrarVacinacao(id);	
			LoteVacina loteTomado = vacinacao.getLoteVacina();
			
			List<LoteVacina> todosLotes = loteVacinaRepository.findAll();
			
			lotesMesmaVacina = todosLotes.stream()
										.filter(lote -> lote.getVacina().getNome().equalsIgnoreCase(loteTomado.getVacina().getNome()))
										.collect(Collectors.toList());		
			return lotesMesmaVacina;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return lotesMesmaVacina;				
		
	}
}
