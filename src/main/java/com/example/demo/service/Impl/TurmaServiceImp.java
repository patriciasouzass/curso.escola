package com.example.demo.service.Impl;

import com.example.demo.infra.exception.TurmaNomeNotFoundException;
import com.example.demo.infra.jpa.entities.Turma;
import com.example.demo.infra.jpa.repositories.InternalJpaTurmaRepository;
import com.example.demo.service.TurmaService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TurmaServiceImp implements TurmaService {
    @Autowired
    private InternalJpaTurmaRepository repository;

    @Override
    public Turma newTurma(Turma turma) throws TurmaNomeNotFoundException {
        repository.save(turma);
        return turma;
    }

    @Override
    public List<Turma> getAll() {
        return repository.findAll();
    }
}
