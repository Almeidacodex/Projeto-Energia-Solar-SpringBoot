package com.adkdevelopment_test.application.controller;


import com.adkdevelopment_test.application.model.Orcamento;
import com.adkdevelopment_test.application.repository.OrcamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/orcamentos")
public class OrcamentoController {

    private final OrcamentoRepository orcamentoRepository;

    public OrcamentoController(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscarPorId(@PathVariable Long id){
        return orcamentoRepository.findById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
