package com.example.demo.service.Impl;

import com.example.demo.infra.exception.AlunoException;
import com.example.demo.infra.exception.AlunoNomeNotFoundException;
import com.example.demo.infra.exception.AlunoNotFoundException;
import com.example.demo.infra.exception.CpfEmailException;
import com.example.demo.infra.jpa.entities.Aluno;
import com.example.demo.infra.jpa.repositories.InternalJpaAlunoRepository;
import com.example.demo.service.AlunoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
class AlunoServiceImp implements AlunoService {
    @Autowired
    private InternalJpaAlunoRepository repository;

    @Value("${url.api.uf}")
    private static String urlApiUf;

    @Override
    public Aluno newAluno(@RequestBody @Valid Aluno aluno) throws AlunoException, ParseException, CpfEmailException {
        if (maiorDezoito(aluno.getDataNascimento()) < 18) {
            throw new AlunoException();
        }

        List<Aluno> alunos = repository.findAll();
        if (isExistingEmail(aluno.getEmail(), alunos) || isExistingCpf(aluno.getCpf(), alunos)) {
            throw new CpfEmailException();
        }
        return repository.save(aluno);
    }

    @Override
    public List<Aluno> getAllAluno() {
        return repository.findAll();
    }

    @Override
    public Optional<Aluno> getById(Long id) throws AlunoNotFoundException {
        if (!repository.existsById(id)) {
            throw new AlunoNotFoundException(id);
        }
        return repository.findById(id);
    }

    @Override
    public List<Aluno> getByName(String nome) throws AlunoNomeNotFoundException {

        List<Aluno> alunos = repository.findAll();
        if (repository.findAlunoByNomeContainingIgnoreCase(nome).isEmpty() || isExistingAluno(nome, alunos)) {
            throw new AlunoNomeNotFoundException(nome);
        }
        return repository.findAlunoByNomeContainingIgnoreCase(nome);
    }

    @Override
    public Aluno updateAluno(Long id, Aluno aluno) {
//        Optional<Aluno> alunoExistente = getById(id);
        repository.save(aluno);
        return aluno;
    }

    @Override
    public void deleteAluno(Long id) throws AlunoNotFoundException {
        if (!repository.existsById(id)) {
            throw new AlunoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public int getYear(String date) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date ano = formato.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ano);
        return calendar.get(Calendar.YEAR);
    }

    public int maiorDezoito(String data) throws ParseException {
        int anoAtual = LocalDate.now().getYear();
        int ano = anoAtual - getYear(data);
        return ano;
    }

    private static boolean isExistingEmail(String email, List<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isExistingCpf(String cpf, List<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isExistingAluno(String nome, List<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            if (!aluno.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }
        return true;
    }
//
//    @Override
//    public Object getEstado(String sigla) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        Object estadoUf = restTemplate.getForObject(urlApiUf, Object.class);
//
//        if (estadoUf == null || !estadoUf.equals(sigla)) {
//            throw new RuntimeException("UF nao localizada");
//        }
//
//        return estadoUf;
//    }
//
//    private static boolean isExistingUF(String sigla, List<Aluno> alunos) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        Object estadoUf = restTemplate.getForObject(urlApiUf, Object.class);
//
//        for (Aluno aluno : alunos) {
//            if (aluno.getUf().equalsIgnoreCase(sigla)) {
//                return estadoUf;
//            }
//        }
//        return false;
//    }
}
