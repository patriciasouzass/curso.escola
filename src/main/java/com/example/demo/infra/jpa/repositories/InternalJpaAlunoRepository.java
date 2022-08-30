package com.example.demo.infra.jpa.repositories;

import com.example.demo.infra.jpa.entities.Aluno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalJpaAlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAlunoByNomeContainingIgnoreCase(String nome);

}
