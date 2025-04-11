package com.desafio.backend_credito_consulta.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Credito {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String numeroCredito;
        private String numeroNfse;
        private LocalDate dataConstituicao;
        private BigDecimal valorIssqn;
        private String tipoCredito;
        private boolean simplesNacional;
        private BigDecimal aliquota;
        private BigDecimal valorFaturado;
        private BigDecimal valorDeducao;
        private BigDecimal baseCalculo;


    public Long getId() {
        return id;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public String getNumeroNfse() {
        return numeroNfse;
    }

    public LocalDate getDataConstituicao() {
        return dataConstituicao;
    }

    public BigDecimal getValorIssqn() {
        return valorIssqn;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public boolean isSimplesNacional() {
        return simplesNacional;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getValorFaturado() {
        return valorFaturado;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public void setNumeroNfse(String numeroNfse) {
        this.numeroNfse = numeroNfse;
    }

    public void setDataConstituicao(LocalDate dataConstituicao) {
        this.dataConstituicao = dataConstituicao;
    }

    public void setValorIssqn(BigDecimal valorIssqn) {
        this.valorIssqn = valorIssqn;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public void setSimplesNacional(boolean simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public void setValorFaturado(BigDecimal valorFaturado) {
        this.valorFaturado = valorFaturado;
    }

    public void setValorDeducao(BigDecimal valorDeducao) {
        this.valorDeducao = valorDeducao;
    }

    public void setBaseCalculo(BigDecimal baseCalculo) {
        this.baseCalculo = baseCalculo;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Credito credito = (Credito) o;
        return simplesNacional == credito.simplesNacional && Objects.equals(id, credito.id) && Objects.equals(numeroCredito, credito.numeroCredito) && Objects.equals(numeroNfse, credito.numeroNfse) && Objects.equals(dataConstituicao, credito.dataConstituicao) && Objects.equals(valorIssqn, credito.valorIssqn) && Objects.equals(tipoCredito, credito.tipoCredito) && Objects.equals(aliquota, credito.aliquota) && Objects.equals(valorFaturado, credito.valorFaturado) && Objects.equals(valorDeducao, credito.valorDeducao) && Objects.equals(baseCalculo, credito.baseCalculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCredito, numeroNfse, dataConstituicao, valorIssqn, tipoCredito, simplesNacional, aliquota, valorFaturado, valorDeducao, baseCalculo);
    }

    @Override
    public String toString() {
        return "Credito{" +
                "id=" + id +
                ", numeroCredito='" + numeroCredito + '\'' +
                ", numeroNfse='" + numeroNfse + '\'' +
                ", dataConstituicao=" + dataConstituicao +
                ", valorIssqn=" + valorIssqn +
                ", tipoCredito='" + tipoCredito + '\'' +
                ", simplesNacional=" + simplesNacional +
                ", aliquota=" + aliquota +
                ", valorFaturado=" + valorFaturado +
                ", valorDeducao=" + valorDeducao +
                ", baseCalculo=" + baseCalculo +
                '}';
    }

    public Credito(String numeroCredito, String numeroNfse, LocalDate dataConstituicao, BigDecimal valorIssqn, String tipoCredito, boolean simplesNacional, BigDecimal aliquota, BigDecimal valorFaturado, BigDecimal valorDeducao, BigDecimal baseCalculo) {
        this.id = id;
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
}

