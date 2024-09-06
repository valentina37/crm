package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Informe;

public interface InformeRepository extends MongoRepository<Informe, String>{

}
