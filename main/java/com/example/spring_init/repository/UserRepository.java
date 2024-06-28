package com.example.spring_init.repository;

import com.example.spring_init.model.mongo.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findUserByName(String user);

    UserModel save(UserModel user);
}
