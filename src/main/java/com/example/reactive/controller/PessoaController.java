package com.example.reactive.controller;

import java.time.Duration;

import com.example.reactive.domain.Pessoa;
import com.example.reactive.repository.PessoaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PessoaController {
   
   private final PessoaRepository repository;
   
   public PessoaController(PessoaRepository repository){
       this.repository = repository;
   }
   
   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public @ResponseBody Mono<Pessoa> save(@RequestBody Pessoa pessoa) {
      return repository.save(pessoa);
   }
 
   @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   @ResponseStatus(HttpStatus.OK)
   public Flux<Pessoa> findAll() {
      return repository.findAll().delayElements(Duration.ofSeconds(2));
   }
}
