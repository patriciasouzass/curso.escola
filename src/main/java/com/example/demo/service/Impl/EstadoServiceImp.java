package com.example.demo.service.Impl;

import com.example.demo.infra.jpa.entities.Aluno;
import com.example.demo.infra.jpa.repositories.InternalJpaAlunoRepository;
import com.example.demo.model.Estado;
import com.example.demo.service.EstadoService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EstadoServiceImp implements EstadoService {

    @Value("${url.api.estados}")
    private String urlApi;
    @Value("${url.api.uf}")
    private String urlApiUf;


    @Override
    public List<Object> getEstados() {
        RestTemplate restTemplate = new RestTemplate();

        Object[] states = restTemplate.getForObject(urlApi, Object[].class);

        assert states != null;
        return Arrays.asList(states);
    }

    @Override
    public Object getEstado(Long municipio) {
        RestTemplate restTemplate = new RestTemplate();

        Object estadoUf = restTemplate.getForObject(urlApiUf, Object.class, municipio);

        return estadoUf;
    }

    private static boolean isExistingUF(String sigla, List<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            if (aluno.getUf().equalsIgnoreCase(sigla)) {
                return true;
            }
        }
        return false;
    }
}
