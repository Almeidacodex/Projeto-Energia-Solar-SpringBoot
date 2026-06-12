package com.adkdevelopment_test.application.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String telefone;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private LocalDateTime criadoEm;
}
