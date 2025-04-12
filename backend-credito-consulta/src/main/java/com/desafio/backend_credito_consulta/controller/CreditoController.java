package com.desafio.backend_credito_consulta.controller;

import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.service.CreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<CreditoDTO> criar(@RequestBody @Valid CreditoDTO dto) {
        CreditoDTO criado = service.salvarCredito(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{numeroCredito}")
    public ResponseEntity<CreditoDTO> atualizar(@PathVariable String numeroCredito, @RequestBody @Valid CreditoDTO dto) {
        CreditoDTO atualizado = service.atualizarCredito(numeroCredito, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{numeroCredito}")
    public ResponseEntity<Void> deletar(@PathVariable String numeroCredito) {
        service.deletarCredito(numeroCredito);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
