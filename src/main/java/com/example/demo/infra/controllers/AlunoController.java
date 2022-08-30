package com.example.demo.infra.controllers;

import com.example.demo.infra.exception.AlunoException;
import com.example.demo.infra.exception.AlunoNomeNotFoundException;
import com.example.demo.infra.exception.AlunoNotFoundException;
import com.example.demo.infra.exception.CpfEmailException;
import com.example.demo.infra.jpa.entities.Aluno;
import com.example.demo.service.AlunoService;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alunos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {
    private AlunoService service;

    @PostMapping(value = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno newAluno(@RequestBody @Valid Aluno aluno) throws AlunoException, ParseException, CpfEmailException {
        return service.newAluno(aluno);
    }

    @GetMapping(value = "/pesquisar/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> getAllAluno() {
        return service.getAllAluno();
    }

    @GetMapping(value = "/pesquisar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> getById(@PathVariable Long id) throws AlunoNotFoundException {
        return service.getById(id);
    }

    @GetMapping(value = "/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> getByName(@PathVariable String nome) throws AlunoNomeNotFoundException {
        return service.getByName(nome);
    }

    @PutMapping(value = "/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        return service.updateAluno(id, aluno);
    }

    @DeleteMapping(value = "/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Long id) throws AlunoNotFoundException {
        service.deleteAluno(id);
    }

}
