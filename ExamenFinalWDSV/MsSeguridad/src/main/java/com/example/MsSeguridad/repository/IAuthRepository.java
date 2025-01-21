package com.example.MsSeguridad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MsSeguridad.model.AcessModel;
 

 

@Repository
public interface IAuthRepository extends MongoRepository<AcessModel, Integer> {
}
