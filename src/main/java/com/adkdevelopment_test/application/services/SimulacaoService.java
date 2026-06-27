package com.adkdevelopment_test.application.services;

import com.adkdevelopment_test.application.model.Orcamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimulacaoService {

   public Orcamento calcular(Orcamento orcamento){

       BigDecimal consumo = orcamento.getConsumoKwh();
       BigDecimal tarifa  = orcamento.getTarifaKwh();
       BigDecimal irradiacao = orcamento.getIrradiacao();

       if (consumo == null || consumo.compareTo(BigDecimal.ZERO) <=0){
           throw new IllegalArgumentException("Consumo deve ser maior que zero");
       }
       if (tarifa  == null
               || tarifa.compareTo(BigDecimal.ZERO)<=0
               || tarifa.compareTo(new BigDecimal("5.0"))>0)
       {
           throw new IllegalArgumentException("Tarifa inválida. Use um valor  entre 0 e R$ 5,00/kWh.");
       }
       if (irradiacao == null || irradiacao.compareTo(BigDecimal.ZERO) <=0){
           throw new IllegalArgumentException("Irradiação deve ser maior do que zero");
       }

       BigDecimal percentualGeracao = orcamento.getPercentualGeracao()
               .divide(new BigDecimal("100"),6, RoundingMode.HALF_UP);

       BigDecimal consumoAlvo = consumo.multiply(percentualGeracao);

       BigDecimal divisor = irradiacao
               .multiply(new BigDecimal("30"))
               .multiply(new BigDecimal("0.82"));

       BigDecimal potencia = consumoAlvo.divide(divisor, 6 , RoundingMode.HALF_UP);

       BigDecimal custo = potencia
               .multiply(new BigDecimal("1000"))
               .multiply(new BigDecimal("4.5"));

       BigDecimal economiaMensal = consumoAlvo.multiply(tarifa);

       BigDecimal economiaAnual = economiaMensal.multiply(BigDecimal.valueOf(12));

       BigDecimal reajuste = orcamento.getReajusteAnual()
               .divide(new BigDecimal("100"),6, RoundingMode.HALF_UP);

       BigDecimal acumulado = BigDecimal.ZERO;
       Integer payback = null;
       BigDecimal ecoAno = economiaAnual;

       for (int ano = 1; ano <= 25 ; ano++) {
           acumulado = acumulado.add(ecoAno);
           if (acumulado.compareTo(custo) >= 0 && payback == null){
               payback = ano;
           }
           ecoAno = ecoAno.multiply(BigDecimal.ONE.add(reajuste));
       }

       int paybackFinal = (payback != null) ? payback : 99;

        orcamento.setPotenciaKwp(potencia.setScale(2,RoundingMode.HALF_UP));
        orcamento.setCustoEstimado(custo.setScale(2, RoundingMode.HALF_UP));
        orcamento.setEconomiaMensal(economiaMensal.setScale(2, RoundingMode.HALF_UP));
        orcamento.setPaybackAnos(BigDecimal.valueOf(paybackFinal));

        return orcamento;



   }

}
