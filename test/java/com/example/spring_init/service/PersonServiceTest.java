package com.example.spring_init.service;

import com.example.spring_init.model.mongo.Person;
import com.example.spring_init.model.request.CreatePersonModel;
import com.example.spring_init.repository.PersonRepository;
import com.example.spring_init.utils.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest(properties = "spring.mongodb.embedded.version=4.4.11")
public class PersonServiceTest {

    @Autowired
    PersonRepository personRepository;
    PersonService personService;

    @BeforeEach
    public void init() {
        this.personService = new PersonService(personRepository);
        personRepository.deleteAll();
    }


    @Test
    public void testAddPerson() {

        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Person person = personService.addPerson(personModel.toPerson());

        Assertions.assertEquals(personModel.getName(), person.getName(), "Person added should be same");
    }

    @Test
    public void testGetPerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Person person = personService.addPerson(personModel.toPerson());

        Person fetchedPerson = personService.getPerson(person.getName());

        Assertions.assertEquals(personModel.getName(), fetchedPerson.getName(), "Person should be fetched properly");
    }

    @Test
    public void testGetAllPerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Person person = personService.addPerson(personModel.toPerson());

        List<Person> fetchedPerson = personService.getAllPersons();
        Assertions.assertNotNull(fetchedPerson);
        Assertions.assertEquals(personModel.getName(), fetchedPerson.get(0).getName(), "Person should be fetched properly");
    }

    @Test
    public void testDeletePerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Person person = personService.addPerson(personModel.toPerson());

        Person fetchedPerson = personService.deletePerson(person.getName());

        Assertions.assertEquals(personModel.getName(), fetchedPerson.getName(), "Person should be deleted");

        Person deletedPerson = personService.getPerson(person.getName());

        Assertions.assertNull(deletedPerson, "Person should be deleted");
    }
}
