package com.example.spring_init.controller;

import com.example.spring_init.model.mongo.Person;
import com.example.spring_init.model.request.CreatePersonModel;
import com.example.spring_init.repository.PersonRepository;
import com.example.spring_init.service.PersonService;
import com.example.spring_init.utils.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class PersonControllerTest {

    PersonController personController;

    PersonService personService;

    @MockBean
    PersonRepository personRepository;


    @BeforeEach
    public void init() {
        personService = new PersonService(personRepository);
        personController = new PersonController(personService);
        personRepository.deleteAll();
    }

    @Test
    public void testAddPerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Mockito.when(personRepository.save(personModel.toPerson())).thenReturn(personModel.toPerson());

        Person person = personController.addPerson(personModel);

        Assertions.assertEquals(person.getName(), personModel.getName(), "Person should be added");
        Assertions.assertEquals(person.getPinCode(), personModel.getPinCode(), "PinCode should be added");
    }

    @Test
    public void testGetPerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Mockito.when(personRepository.findPersonByName(personModel.getName())).thenReturn(personModel.toPerson());
        Person person = personController.getPerson(personModel.getName());

        Assertions.assertEquals(person.getName(), personModel.getName(), "Person should be added");
        Assertions.assertEquals(person.getPinCode(), personModel.getPinCode(), "PinCode should be added");
    }

    @Test
    public void testGetAllPersons() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Mockito.when(personRepository.findAll()).thenReturn(List.of(personModel.toPerson()));
        List<Person> persons = personController.getAllPersons();
        Assertions.assertNotNull(persons);
        Assertions.assertEquals(persons.get(0).getName(), personModel.getName(), "Person should be added");
        Assertions.assertEquals(persons.get(0).getPinCode(), personModel.getPinCode(), "PinCode should be added");
    }

    @Test
    public void testDeletePerson() {
        CreatePersonModel personModel = EntityBuilder.getcreatePersonModel("name", 560068);
        Mockito.when(personRepository.deleteByName(personModel.getName())).thenReturn(personModel.toPerson());
        Person person = personController.deletePerson(personModel.getName());
        Assertions.assertEquals(person.getName(), personModel.getName(), "Person should be added");
        Assertions.assertEquals(person.getPinCode(), personModel.getPinCode(), "PinCode should be added");

        Person deleted_person = personController.getPerson(personModel.getName());
        Assertions.assertNull(deleted_person, "Person should be deleted");
    }

}
