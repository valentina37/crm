package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
	List<Usuario> findByEstado(String estado);
}
