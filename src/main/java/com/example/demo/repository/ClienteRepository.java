package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Cliente;


public interface ClienteRepository extends MongoRepository<Cliente, String>{
	List<Cliente> findByEstado(String estado);
}
