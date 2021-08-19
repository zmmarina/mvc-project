package com.gft.desafiomvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.Vacinacao;

@Repository
public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long>{
	
	
	@Query(value="select * from vacinacao v JOIN pessoa p WHERE v.pessoa = p.id AND cpf like %:cpf%", nativeQuery=true)
	List<Vacinacao> encontrarVacinacaoPacienteId(@Param("cpf") String cpf);
	
	@Query(value="select * from vacinacao v JOIN pessoa p WHERE v.pessoa = p.id AND dose like %:dose%", nativeQuery=true)
	List<Vacinacao> encontrarPacienteDose(@Param("dose") String dose);
	

}
