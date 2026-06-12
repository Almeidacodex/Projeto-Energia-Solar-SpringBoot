package com.adkdevelopment_test.application.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal tarifaKwh;
    private BigDecimal irradiacao;
    private BigDecimal percentualGeracao;
    private BigDecimal reajusteAnual;

    private BigDecimal potenciaKwp;
    private BigDecimal custoEstimado;
    private BigDecimal economiaMensal;
    private BigDecimal paybackAnos;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;

    private String observacoes;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

}
