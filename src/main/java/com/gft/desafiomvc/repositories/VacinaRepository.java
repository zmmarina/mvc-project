package com.gft.desafiomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafiomvc.entities.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long>{

}
