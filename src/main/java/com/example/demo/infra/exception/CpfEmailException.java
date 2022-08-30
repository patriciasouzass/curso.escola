package com.example.demo.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfEmailException extends Exception {
    public CpfEmailException() {
        super("CPF ou e-mail já cadastrado no sistema.");
    }


}
