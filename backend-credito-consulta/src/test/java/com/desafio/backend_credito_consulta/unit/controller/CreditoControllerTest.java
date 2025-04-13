package com.desafio.backend_credito_consulta.unit.controller;

import com.desafio.backend_credito_consulta.controller.CreditoController;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.exception.CreditoNotFoundException;
import com.desafio.backend_credito_consulta.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditoControllerTest {

    @InjectMocks
    private CreditoController controller;

    @Mock
    private CreditoService service;

    private CreditoDTO dto;

    @BeforeEach
    void setUp() {
        dto = new CreditoDTO();
        dto.setNumeroCredito("CRED123");
        dto.setNumeroNfse("NFSE456");
        dto.setTipoCredito("ISSQN");
        dto.setSimplesNacional("Sim");
    }


    @Test
    void deveBuscarCreditoPorNumeroComSucesso() {
        when(service.consultarPorNumeroCredito("CRED123")).thenReturn(dto);

        ResponseEntity<CreditoDTO> response = controller.getPorNumeroCredito("CRED123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CRED123", response.getBody().getNumeroCredito());
        verify(service).consultarPorNumeroCredito("CRED123");
    }

    @Test
    void deveRetornarListaCreditoDTOPorNfseComSucesso() {
        when(service.consultarPorNfse("NFSE456")).thenReturn(Collections.singletonList(dto));

        ResponseEntity<List<CreditoDTO>> response = controller.getPorNfse("NFSE456");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("NFSE456", response.getBody().get(0).getNumeroNfse());
        verify(service).consultarPorNfse("NFSE456");
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoEncontrarNfse() {
        when(service.consultarPorNfse("NFSE000")).thenReturn(Collections.emptyList());

        ResponseEntity<List<CreditoDTO>> response = controller.getPorNfse("NFSE000");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(service).consultarPorNfse("NFSE000");
    }

    @Test
    void deveCriarCreditoComSucesso() {
        when(service.salvarCredito(dto)).thenReturn(dto);

        ResponseEntity<CreditoDTO> response = controller.criar(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("CRED123", response.getBody().getNumeroCredito());
        verify(service).salvarCredito(dto);
    }

    @Test
    void deveAtualizarCreditoComSucesso() {
        when(service.atualizarCredito("CRED123", dto)).thenReturn(dto);

        ResponseEntity<CreditoDTO> response = controller.atualizar("CRED123", dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CRED123", response.getBody().getNumeroCredito());
        verify(service).atualizarCredito("CRED123", dto);
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarCreditoInexistente() {
        when(service.atualizarCredito("CRED999", dto))
                .thenThrow(new CreditoNotFoundException("Crédito não encontrado"));

        CreditoNotFoundException ex = assertThrows(CreditoNotFoundException.class,
                () -> controller.atualizar("CRED999", dto));
        assertEquals(ex.getMessage(),"Crédito não encontrado");
        verify(service).atualizarCredito("CRED999", dto);
    }

    @Test
    void deveDeletarCreditoComSucesso() {
        doNothing().when(service).deletarCredito("CRED123");

        ResponseEntity<Void> response = controller.deletar("CRED123");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service).deletarCredito("CRED123");
    }

    @Test
    void deveLancarExcecaoAoDeletarCreditoInexistente() {
        doThrow(new CreditoNotFoundException("Crédito não encontrado"))
                .when(service).deletarCredito("CRED999");

        CreditoNotFoundException ex = assertThrows(CreditoNotFoundException.class,
                () -> controller.deletar("CRED999"));
        assertEquals(ex.getMessage(), "Crédito não encontrado");
        verify(service).deletarCredito("CRED999");
    }

}
