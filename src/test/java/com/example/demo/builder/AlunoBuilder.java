package com.example.demo.builder;

import com.example.demo.infra.jpa.entities.Turma;
import lombok.Builder;

public class AlunoBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private Long matricula = 1111L;

    @Builder.Default
    private String nome = "Jéssica";

    @Builder.Default
    private String sobrenome = "Carvalho";

    @Builder.Default
    private String dataNascimento = "01/01/1988";

    @Builder.Default
    private String email = "jessica@email.com";

    @Builder.Default
    private String cpf = "09412337400";

    @Builder.Default
    private String endereco;

    @Builder.Default
    private Turma turma = new Turma(2L, "TurmaC", "Noite");

//    public AlunoBuilder toAluno() {
//        return new AlunoBuilder(id,
//                matricula,
//                nome,
//                sobrenome,
//                dataNascimento,
//                email,
//                cpf,
//                endereco,
//                turma);
//    }
}
