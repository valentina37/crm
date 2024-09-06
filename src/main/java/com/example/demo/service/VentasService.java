package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ventas;
import com.example.demo.repository.VentasRepository;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    public List<Ventas> obtenerTodasVentas() {
        return ventasRepository.findAll();
    }

    public Ventas guardarVenta(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    public void eliminarVenta(String id) {
        ventasRepository.deleteById(id);
    }

    public Ventas obtenerVentaPorId(String id) {
        return ventasRepository.findById(id).orElse(null);
    }

    
}
