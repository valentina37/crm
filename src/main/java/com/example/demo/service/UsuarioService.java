package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuariosHabilitados() {
        return usuarioRepository.findByEstado("Habilitado");
    }
}
