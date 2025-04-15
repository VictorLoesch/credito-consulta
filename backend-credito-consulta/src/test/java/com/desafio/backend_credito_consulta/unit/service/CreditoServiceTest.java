package com.desafio.backend_credito_consulta.unit.service;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.exception.CreditoNotFoundException;
import com.desafio.backend_credito_consulta.repository.CreditoRepository;
import com.desafio.backend_credito_consulta.service.ConsultaKafkaPublisher;
import com.desafio.backend_credito_consulta.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private ConsultaKafkaPublisher publisher;

    @InjectMocks
    private CreditoService creditoService;

    private Credito credito;

    @BeforeEach
    void setUp() {
        credito = new Credito();
        credito.setNumeroCredito("CRED001");
        credito.setNumeroNfse("NFSE123");
    }


    @Test
    void deveRetornarListaCreditoDTOQuandoBuscarPorNfse() {
        when(creditoRepository.findByNumeroNfse("NFSE123")).thenReturn(Collections.singletonList(credito));
        doNothing().when(publisher).publicarEventoConsulta(anyString());
        List<CreditoDTO> resultado = creditoService.consultarPorNfse("NFSE123");
        assertEquals(1, resultado.size());
        assertEquals("NFSE123",resultado.get(0).getNumeroNfse());
        verify(creditoRepository).findByNumeroNfse("NFSE123");
        verify(publisher).publicarEventoConsulta(anyString());
    }

    @Test
    void deveRetornarListaVaziaQuandoBuscarPorNfseInexistente() {
        when(creditoRepository.findByNumeroNfse("NFSE9")).thenReturn(Collections.emptyList());
        doNothing().when(publisher).publicarEventoConsulta(anyString());
        List<CreditoDTO> resultado = creditoService.consultarPorNfse("NFSE9");
        assertTrue(resultado.isEmpty(), "A lista deve estar vazia");
        verify(creditoRepository).findByNumeroNfse("NFSE9");
        verify(publisher).publicarEventoConsulta(anyString());
    }

    @Test
    void deveRetornarCreditoDTOQuandoBuscarPorNumeroCredito() {
        when(creditoRepository.findByNumeroCredito("CRED001")).thenReturn(Optional.of(credito));
        doNothing().when(publisher).publicarEventoConsulta(anyString());
        CreditoDTO dto = creditoService.consultarPorNumeroCredito("CRED001");
        assertEquals("CRED001", dto.getNumeroCredito());
        verify(creditoRepository).findByNumeroCredito("CRED001");
        verify(publisher).publicarEventoConsulta(anyString());
    }

    @Test
    void deveLancarExcecaoQuandoBuscarPorNumeroCreditoInexistente() {
        when(creditoRepository.findByNumeroCredito("000")).thenReturn(Optional.empty());
        doNothing().when(publisher).publicarEventoConsulta(anyString());
        CreditoNotFoundException ex =  assertThrows(
                CreditoNotFoundException.class, () -> creditoService.consultarPorNumeroCredito("000"));
        assertEquals("Crédito não encontrado",ex.getMessage());
        verify(creditoRepository).findByNumeroCredito("000");
        verify(publisher).publicarEventoConsulta(anyString());
    }

    @Test
    void deveSalvarCreditoComSucesso() {
        when(creditoRepository.save(any())).thenReturn(credito);
        CreditoDTO dto = new CreditoDTO();
        dto.setNumeroCredito("CRED001");
        dto.setNumeroNfse("NFSE123");

        CreditoDTO salvo = creditoService.salvarCredito(dto);

        assertEquals("CRED001", salvo.getNumeroCredito());
        verify(creditoRepository).save(any(Credito.class));
    }

    @Test
    void deveAtualizarCreditoComSucesso() {

        CreditoDTO dtoAtualizado = new CreditoDTO();
        dtoAtualizado.setNumeroCredito("CRED001");
        dtoAtualizado.setNumeroNfse("NOVO789");

        when(creditoRepository.findByNumeroCredito("CRED001"))
                .thenReturn(Optional.of(credito));

        when(creditoRepository.save(any(Credito.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreditoDTO resultado = creditoService.atualizarCredito("CRED001", dtoAtualizado);

        assertEquals("CRED001", resultado.getNumeroCredito());
        assertEquals("NOVO789", resultado.getNumeroNfse());

        verify(creditoRepository).findByNumeroCredito("CRED001");
        verify(creditoRepository).save(any(Credito.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarCreditoInexistente() {
        when(creditoRepository.findByNumeroCredito("CRED001")).thenReturn(Optional.empty());
        CreditoDTO dto = new CreditoDTO();
        CreditoNotFoundException ex = assertThrows(
                CreditoNotFoundException.class, () -> creditoService.atualizarCredito("CRED001", dto));
        assertEquals("Crédito não encontrado",ex.getMessage());
        verify(creditoRepository).findByNumeroCredito("CRED001");
    }

    @Test
    void deveDeletarCreditoComSucesso() {
        when(creditoRepository.findByNumeroCredito("CRED001")).thenReturn(Optional.of(credito));
        doNothing().when(creditoRepository).delete(credito);

        creditoService.deletarCredito("CRED001");

        verify(creditoRepository).findByNumeroCredito("CRED001");
        verify(creditoRepository).delete(credito);
    }

    @Test
    void deveLancarExcecaoAoDeletarCreditoInexistente() {
        when(creditoRepository.findByNumeroCredito("000")).thenReturn(Optional.empty());
        assertThrows(CreditoNotFoundException.class, () -> creditoService.deletarCredito("000"));
        verify(creditoRepository).findByNumeroCredito("000");
    }


}
