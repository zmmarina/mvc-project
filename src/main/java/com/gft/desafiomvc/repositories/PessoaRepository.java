package com.gft.desafiomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query(value="select * from pessoa p WHERE p.cpf like %:cpf%", nativeQuery=true)
	Pessoa encontrarPessoaCPF(@Param("cpf") String cpf);	
	
	


}
