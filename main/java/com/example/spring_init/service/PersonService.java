package com.example.spring_init.service;

import com.example.spring_init.model.mongo.Person;
import com.example.spring_init.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    public PersonService(PersonRepository personRepository) {
        repository = personRepository;
    }

    public Person addPerson(Person person) {
        logger.info("Adding person in service" + person);
         return repository.save(person);
    }

    public Person getPerson(String name) {
        logger.info("Fetching person with name : {}", name);
        return repository.findPersonByName(name);
    }

    public List<Person> getAllPersons() {
        logger.info("Fetching all persons");
        return repository.findAll();
    }

    public Person deletePerson(String name) {
        logger.info("Deleting person with name : {}", name);
        return repository.deleteByName(name);
    }
}
