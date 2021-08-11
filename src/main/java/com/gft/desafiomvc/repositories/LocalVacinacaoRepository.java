package com.gft.desafiomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.LocalVacinacao;

@Repository
public interface LocalVacinacaoRepository extends JpaRepository<LocalVacinacao, Long> {

}
