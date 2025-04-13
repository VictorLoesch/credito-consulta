package com.desafio.backend_credito_consulta.unit.factory;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;
import com.desafio.backend_credito_consulta.factory.CreditoDTOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditoDTOFactoryTest {

    private Credito credito;
    private CreditoDTO dto;

    @BeforeEach
    void setUp() {
        credito = new Credito();
        credito.setNumeroCredito("CRED001");
        credito.setNumeroNfse("NFSE123");
        credito.setValorIssqn(new BigDecimal("2000.00"));
        credito.setSimplesNacional(true);

        dto = new CreditoDTO();
        dto.setNumeroCredito("CRED002");
        dto.setNumeroNfse("NFSE456");
        dto.setSimplesNacional("Não");
        dto.setTipoCredito("ISSQN");
        dto.setValorFaturado(new BigDecimal("10000.00"));
    }

    @Test
    void deveConverterEntityParaDTOComSucesso() {
        CreditoDTO resultado = CreditoDTOFactory.fromEntity(credito);
        assertEquals("CRED001", resultado.getNumeroCredito());
        assertEquals("NFSE123", resultado.getNumeroNfse());
        assertEquals("Sim", resultado.getSimplesNacional());
    }

    @Test
    void deveConverterDTOParaEntityComSucesso() {
        Credito resultado = CreditoDTOFactory.toEntity(dto);
        assertEquals("CRED002", resultado.getNumeroCredito());
        assertEquals("NFSE456", resultado.getNumeroNfse());
        assertFalse(resultado.isSimplesNacional());
    }

    @Test
    void deveLancarNullPointerQuandoDTOForNulo() {
        assertThrows(NullPointerException.class, () -> {
            CreditoDTOFactory.toEntity(null);
        });
    }

    @Test
    void deveLancarNullPointerQuandoEntityForNula() {
        assertThrows(NullPointerException.class, () -> {
            CreditoDTOFactory.fromEntity(null);
        });
    }

}
