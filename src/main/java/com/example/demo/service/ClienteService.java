package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientesHabilitados() {
        return clienteRepository.findByEstado("Habilitado");
    }
}
