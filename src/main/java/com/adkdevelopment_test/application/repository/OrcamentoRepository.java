package com.adkdevelopment_test.application.repository;

import com.adkdevelopment_test.application.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
