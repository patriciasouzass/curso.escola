package com.example.demo.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlunoNotFoundException extends Exception {

    public AlunoNotFoundException(Long id){
        super(String.format("Aluno com id %s não encontrado em nossa base de dados", id));
    }
}
