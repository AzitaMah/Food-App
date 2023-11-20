package com.datingfood.backend.api;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    @GetMapping("/person")
    List<Person> all() {
        return personRepository.findAll();
    }

}
