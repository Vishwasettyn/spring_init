package com.example.spring_init.controller;

import com.example.spring_init.model.mongo.Person;
import com.example.spring_init.model.request.CreatePersonModel;
import com.example.spring_init.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonService service;

    public PersonController(PersonService personService) {
        service = personService;
    }

    @PostMapping(path = "/person")
    public Person addPerson(@RequestBody CreatePersonModel createPersonModel){
        return service.addPerson(createPersonModel.toPerson());
    }

    @GetMapping(path = "/person")
    public List<Person> getPerson(@RequestParam(value = "name", required = false) String name) {

        if (name == null || name.isEmpty()) {
            return service.getAllPersons();
        }
        return Collections.singletonList(service.getPerson(name));
    }

    @DeleteMapping(path = "/person/{name}")
    public Person deletePerson(@RequestParam(value = "name") String name) {
        return service.deletePerson(name);
    }

    @GetMapping(path = "/persons")
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }

}
