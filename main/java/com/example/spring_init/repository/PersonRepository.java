package com.example.spring_init.repository;

import com.example.spring_init.model.mongo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findAll();

    Person findPersonByName(String name);

    Person deleteByName(String name);

}
