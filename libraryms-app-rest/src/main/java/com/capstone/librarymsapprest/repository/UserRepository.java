package com.capstone.librarymsapprest.repository;
// UserRepository.java
import com.capstone.librarymsapprest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}

