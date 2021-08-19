package com.gft.desafiomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.entities.Pessoa;
import com.gft.desafiomvc.repositories.PessoaRepository;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Pessoa salvarPessoa(Pessoa pessoa) throws Exception {
		
		if (pessoa.getId() == null) {
			if(pessoaRepository.encontrarPessoaCPF(pessoa.getCpf()) == null) {
				return pessoaRepository.save(pessoa);
			} else {
				throw new Exception("CPF já cadastrado!");
			}				
		}else {
			if(pessoaRepository.encontrarPessoaCPF(pessoa.getCpf()) != null) {
				return pessoaRepository.save(pessoa);
			}
		}
				
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> listarPessoas(){
		
		return pessoaRepository.findAll();
	}
	
	public Pessoa encontrarPessoa(Long id) throws Exception {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if(pessoa.isEmpty()) {
			throw new Exception("Pessoa não encontrada!");
		}
		
		return pessoa.get();
	}
	
	public Pessoa encontrarPessoaCPF(String cpf) {
		
		Pessoa pessoa = pessoaRepository.encontrarPessoaCPF(cpf);
		
		return pessoa;		
	}
	
	public void excluirPessoa(Long id) {
		
		pessoaRepository.deleteById(id);
	}	

}
