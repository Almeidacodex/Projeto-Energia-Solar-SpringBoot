package com.adkdevelopment_test.application.controller;

import com.adkdevelopment_test.application.dto.SimulacaoRequest;
import com.adkdevelopment_test.application.model.Orcamento;
import com.adkdevelopment_test.application.services.SimulacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {

    private final SimulacaoService simulacaoService;

    public SimulacaoController(SimulacaoService simulacaoService){
        this.simulacaoService = simulacaoService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<Orcamento> calcular(@RequestBody @Valid SimulacaoRequest request){

        Orcamento orcamento = new Orcamento();
        orcamento.setConsumoKwh(request.getConsumoKwh());

        if (request.getTarifaKwh() != null){
            orcamento.setTarifaKwh(request.getTarifaKwh());
        }
        if (request.getIrradiacao() != null){
            orcamento.setIrradiacao(request.getIrradiacao());
        }
        if (request.getPercentualGeracao() != null){
            orcamento.setPercentualGeracao(request.getPercentualGeracao());
        }
        if (request.getReajusteAnual() != null){
            orcamento.setReajusteAnual(request.getReajusteAnual());
        }
        Orcamento resultado = simulacaoService.calcular(orcamento);

        return ResponseEntity.ok(resultado);
    }

}
