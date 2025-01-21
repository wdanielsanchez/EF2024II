package com.example.MsSeguridad.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// (Opcional) Para mapear nombres de campo si difieren de tu variable
// import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "auth")  // Con esto indicas la colección de Mongo
public class AcessModel {

    @Id
    // Normalmente en Mongo se usa una cadena u ObjectId para el _id,
    // pero si deseas mantenerlo como Integer, es posible, aunque menos común.
    private Integer id_user;

    // @Field("username") // Sólo si quieres un nombre distinto en la colección
    private String username;

    // @Field("password")
    private String password;

    // Getters y Setters
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
