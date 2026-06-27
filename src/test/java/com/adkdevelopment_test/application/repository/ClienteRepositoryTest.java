package com.adkdevelopment_test.application.repository;


import com.adkdevelopment_test.application.model.Cliente;
import com.adkdevelopment_test.application.model.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private  ClienteRepository clienteRepository;

    @Test
    void deveSalvarERecuperarClienteComTodosOsCampos() {
        Cliente cliente = new Cliente();
        cliente.setName("Maria Silva");
        cliente.setEmail("maria@exemplo.com");
        cliente.setTelefone("11999999999");
        cliente.setCidade("São Paulo");
        cliente.setEstado(Estado.SP);

        Cliente salvo = clienteRepository.save(cliente);

        assertThat(salvo.getId()).isNotNull();

        Cliente encontrado = clienteRepository.findById(salvo.getId()).orElseThrow();

        assertThat(encontrado.getName()).isEqualTo("Maria Silva");
        assertThat(encontrado.getEmail()).isEqualTo("maria@exemplo.com");
        assertThat(encontrado.getEstado()).isEqualTo(Estado.SP);
        assertThat(encontrado.getCriadoEm()).isNotNull();
    }

}
