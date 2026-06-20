package com.adkdevelopment_test.application.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // dados de entrada do usuário
    private BigDecimal consumoKwh;
    private BigDecimal contaValor;

    //Parâmetros usados no cálculo
    private BigDecimal tarifaKwh = new BigDecimal("0.85");
    private BigDecimal irradiacao = new BigDecimal("5.0");
    private BigDecimal percentualGeracao = new BigDecimal("100");
    private BigDecimal reajusteAnual = new BigDecimal("8");

    // Resultados calculados
    private BigDecimal potenciaKwp;
    private BigDecimal custoEstimado;
    private BigDecimal economiaMensal;
    private BigDecimal paybackAnos;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status =StatusOrcamento.NOVO;

    private String observacoes;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    public Orcamento(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(BigDecimal consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public BigDecimal getContaValor() {
        return contaValor;
    }

    public void setContaValor(BigDecimal contaValor) {
        this.contaValor = contaValor;
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

    public BigDecimal getPotenciaKwp() {
        return potenciaKwp;
    }

    public void setPotenciaKwp(BigDecimal potenciaKwp) {
        this.potenciaKwp = potenciaKwp;
    }

    public BigDecimal getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(BigDecimal custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public BigDecimal getEconomiaMensal() {
        return economiaMensal;
    }

    public void setEconomiaMensal(BigDecimal economiaMensal) {
        this.economiaMensal = economiaMensal;
    }

    public BigDecimal getPaybackAnos() {
        return paybackAnos;
    }

    public void setPaybackAnos(BigDecimal paybackAnos) {
        this.paybackAnos = paybackAnos;
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
    /**
     * Equivalente a property economia_anual no Django
     */
    @Transient
    public BigDecimal getEconomiaAnual(){
        if (economiaMensal == null){
            return BigDecimal.ZERO;
        }
        return economiaMensal.multiply(BigDecimal.valueOf(12));
    }
    /**
     *
     * Equivalente a property roi_percentual do Django
     */
    @Transient
    public  BigDecimal getRoiPercentual(){
        if (custoEstimado == null || custoEstimado.compareTo(BigDecimal.ZERO)==0){
            return BigDecimal.ZERO;
        }
        return getEconomiaAnual()
                .divide(custoEstimado,4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);
    }
}
