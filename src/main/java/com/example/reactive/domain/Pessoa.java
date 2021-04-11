package com.example.reactive.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "pessoas")
public class Pessoa {
   @Id
   private BigInteger _id;
     
   @Field("codigo")
   private Long codigo;
 
   @Indexed
   @Field("nome")
   private String nome;
}
