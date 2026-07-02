package com.adkdevelopment_test.application.controller;


import com.adkdevelopment_test.application.dto.ClienteRequest;
import com.adkdevelopment_test.application.model.Cliente;
import com.adkdevelopment_test.application.model.Orcamento;
import com.adkdevelopment_test.application.repository.ClienteRepository;
import com.adkdevelopment_test.application.repository.OrcamentoRepository;
import com.adkdevelopment_test.application.services.SimulacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final OrcamentoRepository orcamentoRepository;
    private final SimulacaoService simulacaoService;

    public ClienteController(ClienteRepository clienteRepository,
                             OrcamentoRepository orcamentoRepository,
                             SimulacaoService simulacaoService){
        this.clienteRepository = clienteRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.simulacaoService = simulacaoService;
    }

    @PostMapping
    public ResponseEntity<Orcamento> cadastrar(@RequestBody @Valid ClienteRequest request){

        // Etapa 1: salva o cliente
        Cliente cliente = new Cliente();
        cliente.setName(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setCidade(request.getCidade());
        cliente.setEstado(request.getEstado());
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Etapa 2: monta e calcula o orcamento
        Orcamento orcamento = new Orcamento();
        orcamento.setCliente(clienteSalvo);
        orcamento.setConsumoKwh(request.getConsumoKwh());

        if (request.getTarifaKwh() != null) {
            orcamento.setTarifaKwh(request.getTarifaKwh());
        }
        if (request.getIrradiacao() != null) {
            orcamento.setIrradiacao(request.getIrradiacao());
        }
        if (request.getPercentualGeracao() != null) {
            orcamento.setPercentualGeracao(request.getPercentualGeracao());
        }
        if (request.getReajusteAnual() != null) {
            orcamento.setReajusteAnual(request.getReajusteAnual());
        }
        Orcamento calculado = simulacaoService.calcular(orcamento);

        // Etapa 3: salva o orçamento já calculado
        Orcamento salvo = orcamentoRepository.save(calculado);

        return ResponseEntity.status(201).body(salvo);
    }

}
