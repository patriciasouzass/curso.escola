package com.example.demo.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlunoException extends Exception {
    public AlunoException() {
        super("O aluno precisa ter mais que 18 anos.");
    }
}
