package com.example.demo.infra.controllers;

import com.example.demo.model.Estado;
import com.example.demo.service.EstadoService;
import com.example.demo.service.Impl.EstadoServiceImp;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoService stateServiceImp;

    public EstadoController(EstadoServiceImp estadoServiceImp) {
        this.stateServiceImp = estadoServiceImp;
    }

    @GetMapping()
    public ResponseEntity<List<Object>> getEstados() {

        var service = stateServiceImp.getEstados();

        return ResponseEntity.ok(service);
    }

    @GetMapping("/{municipio}")
    public ResponseEntity<Object> getEstado(@PathVariable(value = "municipio") Long id) {

        var service = stateServiceImp.getEstado(id);

        return ResponseEntity.ok(service);
    }
}
