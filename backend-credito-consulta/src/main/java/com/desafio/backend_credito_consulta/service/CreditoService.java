package com.desafio.backend_credito_consulta.service;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.factory.CreditoDTOFactory;
import com.desafio.backend_credito_consulta.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoService {

    private final CreditoRepository repository;

    public CreditoService(CreditoRepository repository) {
        this.repository = repository;
    }

    public List<CreditoDTO> consultarPorNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse).stream().map(CreditoDTOFactory::fromEntity).collect(Collectors.toList());
    }

    public CreditoDTO consultarPorNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito)
                .map(CreditoDTOFactory::fromEntity)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
    }


}
