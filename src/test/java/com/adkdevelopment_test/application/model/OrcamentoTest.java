package com.adkdevelopment_test.application.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


public class OrcamentoTest {
    @Test
    public void deveCompensarQuandoPaybackForOitoOuMenos(){
        Orcamento orcamento = new Orcamento();
        orcamento.setPaybackAnos(new BigDecimal("8"));

        assertThat(orcamento.isCompensa()).isTrue();
    }
    @Test
    public void naoDeveCompensarQuandoPaybackForMaiorQueOito(){
        Orcamento orcamento = new Orcamento();
        orcamento.setPaybackAnos(new BigDecimal("9"));

        assertThat(orcamento.isCompensa()).isFalse();
    }
    @Test
    public void naoDeveCompensarQuandoPaybackForNulo() {
        Orcamento orcamento = new Orcamento();

        assertThat(orcamento.isCompensa()).isFalse();
    }
    @Test
    public void deveClassificarComoOtimoQuandoPaybackForCincoOuMenos(){
        Orcamento orcamento = new Orcamento();
        orcamento.setPaybackAnos(new BigDecimal("5"));

        assertThat(orcamento.getClassificacao()).isEqualTo(Classificacao.OTIMO);
    }
    @Test
    public void deveClassificarComoRazoavelQuandoPaybackForOito(){
        Orcamento orcamento = new Orcamento();
        orcamento.setPaybackAnos(new BigDecimal("8"));

        assertThat(orcamento.getClassificacao()).isEqualTo(Classificacao.RAZOAVEL);
    }
    @Test
    public void deveClassificarComoNaoCompensaQuandoPaybackForMaiorQueOito(){
        Orcamento orcamento = new Orcamento();
        orcamento.setPaybackAnos(new BigDecimal("9"));

        assertThat(orcamento.getClassificacao()).isEqualTo(Classificacao.NAO_COMPENSA);
    }
    @Test
    void deveClassificarComoNaoCompensaQuandoPaybackForNulo() {
        Orcamento orcamento = new Orcamento();

        assertThat(orcamento.getClassificacao()).isEqualTo(Classificacao.NAO_COMPENSA);
    }
}
