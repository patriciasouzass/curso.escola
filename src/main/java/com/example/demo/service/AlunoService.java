package com.example.demo.service;

import com.example.demo.infra.exception.AlunoException;
import com.example.demo.infra.exception.AlunoNomeNotFoundException;
import com.example.demo.infra.exception.AlunoNotFoundException;
import com.example.demo.infra.exception.CpfEmailException;
import com.example.demo.infra.jpa.entities.Aluno;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AlunoService {
    Aluno newAluno(@RequestBody @Valid Aluno aluno) throws ParseException, AlunoException, CpfEmailException;

//    Aluno newAluno(@RequestBody @Valid Aluno aluno, String cpf, String email) throws AlunoException, ParseException, CpfEmailException;

    List<Aluno> getAllAluno();

    Optional<Aluno> getById(@PathVariable Long id) throws AlunoNotFoundException;

    List<Aluno> getByName(@PathVariable String nome) throws AlunoNomeNotFoundException;

    Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno);

    void deleteAluno(@PathVariable Long id) throws AlunoNotFoundException;

}
