package com.desafio.backend_credito_consulta.controller;

import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.service.CreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> getPorNfse(@PathVariable String numeroNfse) {
        return  ResponseEntity.status(HttpStatus.OK).body(service.consultarPorNfse(numeroNfse));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> getPorNumeroCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorNumeroCredito(numeroCredito));
    }

}
