package com.example.MsSeguridad.service;


import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsSeguridad.model.AcessModel;
import com.example.MsSeguridad.repository.IAuthRepository;
 

 
@Service
public class AuthService {
 
    @Autowired
    IAuthRepository authRepository;
   
    public List<AcessModel>  getAcces() {
       
        return (List<AcessModel>) authRepository.findAll();
    }
   
    public Boolean validatedCredentials(String UserName, String Password) {
        List<AcessModel> result = (List<AcessModel>) authRepository.findAll();
        List<AcessModel> resultFilter = result.stream()
                .filter(t -> t.getUsername().equals(UserName) && t.getPassword().equals(Password))
                .collect(Collectors.toList());
        if (null == resultFilter || resultFilter.isEmpty()) {
            return false;
        }
        return true;
    }
   
}

