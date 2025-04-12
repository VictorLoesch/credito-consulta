package com.desafio.backend_credito_consulta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;


public class CreditoDTO {

    @NotBlank(message = "O número do crédito é obrigatório")
    private String numeroCredito;

    @NotBlank(message = "O número da NFS-e é obrigatório")
    private String numeroNfse;

    @NotNull(message = "A data de constituição é obrigatória")
    private LocalDate dataConstituicao;

    @NotNull(message = "O valor do ISSQN é obrigatório")
    @Positive(message = "O valor do ISSQN deve ser positivo")
    private BigDecimal valorIssqn;

    @NotBlank(message = "O tipo de crédito é obrigatório")
    private String tipoCredito;

    @NotBlank(message = "Informe se é Simples Nacional (Sim/Não)")
    private String simplesNacional;

    @NotNull(message = "A alíquota é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal aliquota;

    @NotNull(message = "O valor faturado é obrigatório")
    @Positive
    private BigDecimal valorFaturado;

    @NotNull(message = "O valor de dedução é obrigatório")
    @PositiveOrZero
    private BigDecimal valorDeducao;

    @NotNull(message = "A base de cálculo é obrigatória")
    @Positive
    private BigDecimal baseCalculo;

    public CreditoDTO() {}

    public CreditoDTO(String numeroCredito, String numeroNfse, LocalDate dataConstituicao,
                      BigDecimal valorIssqn, String tipoCredito, String simplesNacional,
                      BigDecimal aliquota, BigDecimal valorFaturado,
                      BigDecimal valorDeducao, BigDecimal baseCalculo) {
        this.numeroCredito = numeroCredito;
        this.numeroNfse = numeroNfse;
        this.dataConstituicao = dataConstituicao;
        this.valorIssqn = valorIssqn;
        this.tipoCredito = tipoCredito;
        this.simplesNacional = simplesNacional;
        this.aliquota = aliquota;
        this.valorFaturado = valorFaturado;
        this.valorDeducao = valorDeducao;
        this.baseCalculo = baseCalculo;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public String getNumeroNfse() {
        return numeroNfse;
    }

    public void setNumeroNfse(String numeroNfse) {
        this.numeroNfse = numeroNfse;
    }

    public LocalDate getDataConstituicao() {
        return dataConstituicao;
    }

    public void setDataConstituicao(LocalDate dataConstituicao) {
        this.dataConstituicao = dataConstituicao;
    }

    public BigDecimal getValorIssqn() {
        return valorIssqn;
    }

    public void setValorIssqn(BigDecimal valorIssqn) {
        this.valorIssqn = valorIssqn;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public String getSimplesNacional() {
        return simplesNacional;
    }

    public void setSimplesNacional(String simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorFaturado() {
        return valorFaturado;
    }

    public void setValorFaturado(BigDecimal valorFaturado) {
        this.valorFaturado = valorFaturado;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public void setValorDeducao(BigDecimal valorDeducao) {
        this.valorDeducao = valorDeducao;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(BigDecimal baseCalculo) {
        this.baseCalculo = baseCalculo;
    }





}
