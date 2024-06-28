package com.example.spring_init.controller;


import com.example.spring_init.model.mongo.Person;
import com.example.spring_init.model.request.CreatePersonModel;
import com.example.spring_init.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService service;

    public PersonController(PersonService personService) {
        service = personService;
    }

    @PostMapping()
    public Person addPerson(@RequestBody CreatePersonModel person){
        return service.addPerson(person.toPerson());
    }

    @GetMapping("/{name}")
    public Person getPerson(String name) {
        return service.getPerson(name);
    }

    @DeleteMapping("/{name}")
    public Person deletePerson(String name) {
        return service.deletePerson(name);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }
}
