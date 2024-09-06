package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Ventas;
import com.example.demo.repository.VentasRepository;

@Controller
public class ConsultaController {

    private final VentasRepository ventasRepository;

    public ConsultaController(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @GetMapping("/gerente/consultar-ventas")
    public String consultarVentas(Model model) {
        List<Ventas> ventasList = ventasRepository.findAll();
        model.addAttribute("ventas", ventasList);
        return "consultar-ventas";
    }
}
