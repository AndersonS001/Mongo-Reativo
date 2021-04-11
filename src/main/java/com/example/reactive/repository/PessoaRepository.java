package com.example.reactive.repository;

import com.example.reactive.domain.Pessoa;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PessoaRepository extends ReactiveMongoRepository<Pessoa, Long>{
    
}
