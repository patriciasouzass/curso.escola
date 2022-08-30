package com.example.demo.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlunoNomeNotFoundException extends Exception {

    public AlunoNomeNotFoundException(String nome){
        super(String.format("Aluno de nome %s não encontrado em nossa base de dados.", nome));
    }
}
