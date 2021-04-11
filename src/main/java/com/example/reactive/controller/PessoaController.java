package com.example.reactive.controller;

import com.example.reactive.domain.Pessoa;
import com.example.reactive.service.PessoaService;

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

   private final PessoaService pessoaService;

   public PessoaController(PessoaService pessoaService) {
      this.pessoaService = pessoaService;
   }

   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public @ResponseBody Mono<Pessoa> save(@RequestBody Pessoa pessoa) {
      return pessoaService.save(pessoa);
   }

   @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   @ResponseStatus(HttpStatus.OK)
   public Flux<Pessoa> findAll() {
      return pessoaService.findAll();
   }

}
