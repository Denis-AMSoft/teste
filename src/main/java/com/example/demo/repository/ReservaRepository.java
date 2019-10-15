package com.example.demo.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	Collection<? extends Reserva> findByDataFinalBetween(Date dataInicial, Date dataFinal);
	
	Collection<? extends Reserva> findByDataInicialBetween(Date dataInicial, Date dataFinal);
	
}
