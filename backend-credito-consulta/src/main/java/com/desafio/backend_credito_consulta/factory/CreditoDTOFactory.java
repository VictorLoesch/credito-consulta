package com.desafio.backend_credito_consulta.factory;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.dto.CreditoDTO;

public class CreditoDTOFactory {

    private CreditoDTOFactory() {}

    public static CreditoDTO fromEntity(Credito credito) {
        return new CreditoDTO(
                credito.getNumeroCredito(),
                credito.getNumeroNfse(),
                credito.getDataConstituicao(),
                credito.getValorIssqn(),
                credito.getTipoCredito(),
                credito.isSimplesNacional() ? "Sim" : "Não",
                credito.getAliquota(),
                credito.getValorFaturado(),
                credito.getValorDeducao(),
                credito.getBaseCalculo()
        );
    }
}
