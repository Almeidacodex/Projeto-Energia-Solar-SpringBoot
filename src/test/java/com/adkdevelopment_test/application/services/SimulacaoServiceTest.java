package com.adkdevelopment_test.application.services;

import com.adkdevelopment_test.application.model.Orcamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SimulacaoServiceTest {

    @Autowired
    private SimulacaoService simulacaoService;

    @Test
    public void deveCalcularViabilidadeComValoresDefault(){
        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(new BigDecimal("500"));
        // tarifakw, irradiacao,percentualGeracao e reajusteAnual
        // ja vem preenchidos com os valores default da entidade

        Orcamento resultado = simulacaoService.calcular(orcamento);

        assertThat(resultado.getPotenciaKwp()).isEqualTo(new BigDecimal("4.07"));
        assertThat(resultado.getCustoEstimado()).isEqualTo(new BigDecimal("18292.68"));
        assertThat(resultado.getEconomiaMensal()).isEqualTo(new BigDecimal("425.00"));
        assertThat(resultado.getPaybackAnos()).isEqualTo(new BigDecimal("4"));
    }
    @Test
    public void deveLancarExcessaoQuandoConsumoForNuloOuZero(){
        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(BigDecimal.ZERO);

        assertThatThrownBy(() -> simulacaoService.calcular(orcamento))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Consumo deve ser maior que zero");
    }
    @Test
    public void deveLancarExcecaoQuandoTarifaForZeroOuNegativa(){
        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(new BigDecimal("500"));
        orcamento.setTarifaKwh(BigDecimal.ZERO);

        assertThatThrownBy(()-> simulacaoService.calcular(orcamento))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Tarifa inválida. Use um valor  entre 0 e R$ 5,00/kWh.");
    }
    @Test
    public void deveLancarExcecaoQuandoIrradiacaoForZeroOuNegativa(){
        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(new BigDecimal("500"));
        orcamento.setIrradiacao(BigDecimal.ZERO);

        assertThatThrownBy(() -> simulacaoService.calcular(orcamento))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Irradiação deve ser maior do que zero");
    }
    @Test
    public void documentaQueTarifaAcimaDeCincoNaoEhRejeitadaAtualmente(){
        // Este teste documenta uma inconsistência: a mensagem de erro da validação de
        // tarifa promete um limite de R$ 5,00/kWh, mas o código atual só verifica
        // se a tarifa é <= 0. Tarifas acima de 5.0 são aceitas sem erro.
        // Quando a validação de limite superior for implementada, este teste deve falhar
        // e precisará ser atualizado.

        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(new BigDecimal("500"));
        orcamento.setTarifaKwh(new BigDecimal("999"));

        Orcamento resultado = simulacaoService.calcular(orcamento);

        assertThat(resultado.getEconomiaMensal()).isNotNull();
    }


}
