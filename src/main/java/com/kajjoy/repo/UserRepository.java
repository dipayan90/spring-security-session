package com.kajjoy.repo;

import com.kajjoy.model.AuthenticatedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends MongoRepository<AuthenticatedUser,BigInteger> {
    AuthenticatedUser findByUsername(String username);
}
