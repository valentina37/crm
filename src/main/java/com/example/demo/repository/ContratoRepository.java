package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Contrato;

public interface ContratoRepository extends MongoRepository<Contrato, String>{

}
