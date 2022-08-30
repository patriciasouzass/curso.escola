package com.example.demo.infra.controllers;

import com.example.demo.infra.exception.TurmaNomeNotFoundException;
import com.example.demo.infra.jpa.entities.Turma;
import com.example.demo.service.TurmaService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turmas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TurmaController {
    private TurmaService service;

    @PostMapping(value = "/cadastrar")
    public Turma newTurma(Turma turma) throws TurmaNomeNotFoundException {
        return service.newTurma(turma);
    }

    @GetMapping("/pesquisar/todas")
    public List<Turma> getAll() {
        return service.getAll();
    }
}
