package com.adkdevelopment_test.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SimulacaoRequest {

    @NotNull(message = "Consumo é Obrigatório")
    @DecimalMin(value = "0.01", message = "Consumo deve ser maior que zero")
    private BigDecimal consumoKwh;

    @DecimalMin(value = "0.01",message = "Tarifa deve ser maior que zero")
    @DecimalMax(value = "5.0", message = "Tarifa deve ser no máximo R$ 5,00/kWh")
    private BigDecimal tarifaKwh;

    private BigDecimal irradiacao;
    private BigDecimal percentualGeracao;
    private BigDecimal reajusteAnual;

    public BigDecimal getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(BigDecimal consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public BigDecimal getTarifaKwh() {
        return tarifaKwh;
    }

    public void setTarifaKwh(BigDecimal tarifaKwh) {
        this.tarifaKwh = tarifaKwh;
    }

    public BigDecimal getIrradiacao() {
        return irradiacao;
    }

    public void setIrradiacao(BigDecimal irradiacao) {
        this.irradiacao = irradiacao;
    }

    public BigDecimal getPercentualGeracao() {
        return percentualGeracao;
    }

    public void setPercentualGeracao(BigDecimal percentualGeracao) {
        this.percentualGeracao = percentualGeracao;
    }

    public BigDecimal getReajusteAnual() {
        return reajusteAnual;
    }

    public void setReajusteAnual(BigDecimal reajusteAnual) {
        this.reajusteAnual = reajusteAnual;
    }
}
