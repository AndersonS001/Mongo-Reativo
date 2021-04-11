package com.example.reactive.service;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.reactive.domain.Pessoa;
import com.example.reactive.repository.PessoaRepository;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final Environment env;
    private List<Pessoa> mockList;

    public PessoaService(PessoaRepository pessoaRepository, Environment env) {
        this.pessoaRepository = pessoaRepository;
        this.env = env;
        this.mockList = new ArrayList<>();
    }

    private boolean isMock() {
        return env.getProperty("isMock").equals("true");
    }

    public Mono<Pessoa> save(Pessoa pessoa) {
        Mono<Pessoa> save;

        if (isMock()) {
            pessoa.set_id(new BigInteger(4, new Random()));
            mockList.add(pessoa);

            save = Mono.just(pessoa);
        } else {
            save = pessoaRepository.save(pessoa);
        }

        return save;
    }

    public Flux<Pessoa> findAll() {

        Flux<Pessoa> pessoaFlux;

        if (isMock())
            pessoaFlux = Flux.fromIterable(mockList);
        else
            pessoaFlux = pessoaRepository.findAll();

        return pessoaFlux.delayElements(Duration.ofSeconds(1));
    }

}
