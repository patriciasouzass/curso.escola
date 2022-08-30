package com.example.demo.service;

import com.example.demo.infra.exception.TurmaNomeNotFoundException;
import com.example.demo.infra.jpa.entities.Turma;
import java.util.List;

public interface TurmaService {
    Turma newTurma(Turma turma) throws TurmaNomeNotFoundException;

    List<Turma> getAll();
}
