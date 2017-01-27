package com.kajjoy.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document
@Entity
public class AuthenticatedUser implements Serializable{

    public AuthenticatedUser(){}

    public AuthenticatedUser(String user,String password){
        this.username = user;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
}
