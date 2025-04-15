package com.desafio.backend_credito_consulta.service;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.exception.CreditoNotFoundException;
import com.desafio.backend_credito_consulta.factory.CreditoDTOFactory;
import com.desafio.backend_credito_consulta.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoService {

    private final CreditoRepository repository;
    private final ConsultaKafkaPublisher kafkaPublisher;

    public CreditoService(CreditoRepository repository, ConsultaKafkaPublisher kafkaPublisher) {
        this.repository = repository;
        this.kafkaPublisher = kafkaPublisher;
    }

    public List<CreditoDTO> consultarPorNfse(String numeroNfse) {
        kafkaPublisher.publicarEventoConsulta("Consulta realizada para NFSe: " + numeroNfse);
        return repository.findByNumeroNfse(numeroNfse).stream().map(CreditoDTOFactory::fromEntity).collect(Collectors.toList());
    }

    public CreditoDTO consultarPorNumeroCredito(String numeroCredito) {
        kafkaPublisher.publicarEventoConsulta("Consulta realizada para Credito: " + numeroCredito);
        return repository.findByNumeroCredito(numeroCredito)
                .map(CreditoDTOFactory::fromEntity)
                .orElseThrow(() -> new CreditoNotFoundException("Crédito não encontrado"));
    }

    public CreditoDTO salvarCredito(CreditoDTO dto) {
        Credito credito = CreditoDTOFactory.toEntity(dto);
        Credito salvo = repository.save(credito);
        return CreditoDTOFactory.fromEntity(salvo);
    }

    public CreditoDTO atualizarCredito(String numeroCredito, CreditoDTO dto) {
        Credito existente = repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new CreditoNotFoundException("Crédito não encontrado"));

        // Atualiza os dados
        existente.setNumeroNfse(dto.getNumeroNfse());
        existente.setDataConstituicao(dto.getDataConstituicao());
        existente.setValorIssqn(dto.getValorIssqn());
        existente.setTipoCredito(dto.getTipoCredito());
        existente.setSimplesNacional("Sim".equalsIgnoreCase(dto.getSimplesNacional()));
        existente.setAliquota(dto.getAliquota());
        existente.setValorFaturado(dto.getValorFaturado());
        existente.setValorDeducao(dto.getValorDeducao());
        existente.setBaseCalculo(dto.getBaseCalculo());

        Credito atualizado = repository.save(existente);
        return CreditoDTOFactory.fromEntity(atualizado);
    }

    public void deletarCredito(String numeroCredito) {
        Credito credito = repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new CreditoNotFoundException("Crédito não encontrado"));
        repository.delete(credito);
    }

}
