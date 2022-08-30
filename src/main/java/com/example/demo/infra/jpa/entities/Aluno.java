package com.example.demo.infra.jpa.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15, unique = true)
    private Long matricula;

    @NotBlank(message = "Nome não informado")
    @Column(nullable = false, length = 50)
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @NotBlank(message = "Data de Nascimento não informada")
    @Column(nullable = false, length = 10)
    private String dataNascimento;

    @NotBlank(message = "E-mail não informado")
    @Column(nullable = false, length = 60, unique = true)
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "CPF não informado")
    @Column(nullable = false, length = 14, unique = true)
    @CPF(message = "Número de CPF inválido")
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
