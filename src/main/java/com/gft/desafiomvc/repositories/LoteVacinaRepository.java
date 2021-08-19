package com.gft.desafiomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gft.desafiomvc.entities.LoteVacina;

public interface LoteVacinaRepository extends JpaRepository<LoteVacina, Long> {
	
	@Query(value="select * from lote_vacina l WHERE l.identificacao_lote like %:identificacao%", nativeQuery=true)
	LoteVacina encontrarLoteIdentificacao(@Param("identificacao") String identificacao);	

}
