package com.adkdevelopment_test.application.repository;


import com.adkdevelopment_test.application.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

}
