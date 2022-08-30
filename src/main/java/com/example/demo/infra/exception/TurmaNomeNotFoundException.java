package com.example.demo.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TurmaNomeNotFoundException extends Exception {

    public TurmaNomeNotFoundException(String nome){
        super(String.format("Turma de nome %s não encontrado em nossa base de dados.", nome));
    }
}
