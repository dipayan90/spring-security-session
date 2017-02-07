package com.kajjoy.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Document
@Entity
public class AuthenticatedUser implements Serializable{

    public AuthenticatedUser(){}

    public AuthenticatedUser(String usr,String pwd){
        this.username = usr;
        this.password = pwd;
    }

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column
    private String username;

    @Column
    private String password;
}
