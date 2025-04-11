package com.desafio.backend_credito_consulta.repository;

import com.desafio.backend_credito_consulta.Entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
}
