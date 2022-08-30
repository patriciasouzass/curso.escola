package com.example.demo.infra.jpa.repositories;

import com.example.demo.infra.jpa.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalJpaTurmaRepository extends JpaRepository<Turma, Long> {
}
