package com.desafio.backend_credito_consulta.controller;

import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.service.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@Tag(name = "Créditos", description = "Operações relacionadas aos créditos")
public class CreditoController {

    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }


    @Operation(summary = "Buscar créditos por número da NFSe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Créditos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
    })
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> getPorNfse(
            @Parameter(description = "Número da NFSe para busca")
            @PathVariable String numeroNfse) {
        return  ResponseEntity.status(HttpStatus.OK).body(service.consultarPorNfse(numeroNfse));
    }


    @Operation(summary = "Buscar crédito por número do crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crédito encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
    })
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> getPorNumeroCredito(
            @Parameter(description = "Número do crédito para busca")
            @PathVariable String numeroCredito) {
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorNumeroCredito(numeroCredito));
    }


    @Operation(summary = "Criar um novo crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Crédito criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do crédito")
    })
    @PostMapping
    public ResponseEntity<CreditoDTO> criar(
            @Parameter(description = "Dados do crédito para criação")
            @RequestBody @Valid CreditoDTO dto) {
        CreditoDTO criado = service.salvarCredito(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }


    @Operation(summary = "Atualizar um crédito existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crédito atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
    })
    @PutMapping("/{numeroCredito}")
    public ResponseEntity<CreditoDTO> atualizar(
            @Parameter(description = "Número do crédito a ser atualizado")
            @PathVariable String numeroCredito,
            @Parameter(description = "Novos dados do crédito")
            @RequestBody @Valid CreditoDTO dto) {
        CreditoDTO atualizado = service.atualizarCredito(numeroCredito, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }


    @Operation(summary = "Deletar crédito por número do crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Crédito deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
    })
    @DeleteMapping("/{numeroCredito}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "Número do crédito a ser deletado")
            @PathVariable String numeroCredito) {
        service.deletarCredito(numeroCredito);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
