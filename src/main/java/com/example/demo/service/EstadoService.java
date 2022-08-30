package com.example.demo.service;

import com.example.demo.model.Estado;
import java.util.List;

public interface EstadoService {

     List<Object> getEstados();

     Object getEstado(Long id);
}
