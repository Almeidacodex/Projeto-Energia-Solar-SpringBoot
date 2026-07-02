package com.adkdevelopment_test.application.dto;

import com.adkdevelopment_test.application.model.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ClienteRequest {

    @NotBlank(message =  "Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Cidade é Obrigatória")
    private String cidade;

    @NotNull(message = "Estado é obrigatório")
    private Estado estado;

    //Dados do orçamento vinculado
    @NotNull(message = "Consumo é Obrigatório")
    private BigDecimal consumoKwh;

    private BigDecimal tarifaKwh;
    private BigDecimal irradiacao;
    private BigDecimal percentualGeracao;
    private BigDecimal reajusteAnual;
    private BigDecimal telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(BigDecimal telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

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

