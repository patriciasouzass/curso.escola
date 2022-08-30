package com.example.demo.infra.controllers;

import com.example.demo.model.Estado;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EstadoClient {

    private static final String GET_ALL_ESTADOS_API = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private static final String GET_ESTADOS_BY_ID_API = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/{municipio}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
//        callGetAllEstadosAPI();
        callGetEstadoByIdAPI();
    }

    private static void callGetAllEstadosAPI() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_ESTADOS_API, HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }

    private static void callGetEstadoByIdAPI() {
        Map<String, Integer> param = new HashMap<>();
        param.put("id", 1);

        Estado user = restTemplate.getForObject(GET_ESTADOS_BY_ID_API, Estado.class, param);
        System.out.println(user.getNome());
        System.out.println(user.getId());
    }
}
