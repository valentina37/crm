package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Ventas;



public interface VentasRepository  extends MongoRepository<Ventas, String> {

}
