package com.veterinaria.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
public class Proprietario extends Pessoa {

    private String endereco;
    private String telefoneContato;
    private LocalDate dataDeNascimento;

    public LocalDate getDataDeNascimento() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataDeNascimento;
    }

    public Proprietario(String nome, String sobrenome, String cpf, String endereco, String telefoneContato, LocalDate dataDeNascimento) {
        super(nome, sobrenome, cpf);
        this.endereco = endereco;
        this.telefoneContato = telefoneContato;
        this.dataDeNascimento = dataDeNascimento;
    }


    @Override
    public String toString() {
        return "Proprietario{" +
                 super.toString() + '\n' +
                ", endereco='" + endereco + '\n' +
                ", telefoneContato='" + telefoneContato + '\n' +
                ", dataDeNascimento=" + dataDeNascimento + '\'' +
                "} ";
      
    }
  
}
