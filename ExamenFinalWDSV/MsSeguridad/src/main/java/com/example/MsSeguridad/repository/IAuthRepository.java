package com.example.MsSeguridad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MsSeguridad.model.AcessModel;
 

 
@Repository
public interface IAuthRepository extends CrudRepository<AcessModel, Long> {
   
}

