package com.adkdevelopment_test.application.model;

public enum Estado {
    AC("Acre"), AL("Alagoas"), AM("Amazonas"),
    AP("Amapá"), BA("Bahia"),CE("Ceará"),
    DF("Distrito federal"),ES("Espirito Santo"),
    GO("Goias"),MA("Maranhão"),
    MG("Minas Gerais"),MS("Mato grosso do Sul"),
    MT("Mato Grosso"),PA("Pará"),
    PB("Paraíba"),PE("Pernambuco"), PI("Piauí"),
    PR("Paraná"),RJ("Rio de Janeiro"),TO("Tocantins"),
    RN("Rio Grande do Norte"),RO("Rondônia"),SE("Sergipe"),
    SC("Santa Catarina"),SP("São Paulo"),;



    private final String nome;

    Estado(String nome) {
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
