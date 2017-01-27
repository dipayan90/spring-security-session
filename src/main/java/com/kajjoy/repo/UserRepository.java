package com.kajjoy.repo;

import com.kajjoy.model.AuthenticatedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AuthenticatedUser,Long> {
    AuthenticatedUser findByUsername(String username);
}
