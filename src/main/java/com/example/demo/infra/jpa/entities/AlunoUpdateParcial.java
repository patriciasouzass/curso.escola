package com.example.demo.infra.jpa.entities;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alunos")
public class AlunoUpdateParcial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "E-mail não informado")
    @Column(nullable = false, length = 60, unique = true)
    @Email(message = "E-mail inválido")
    private String email;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
}
