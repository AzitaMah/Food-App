package com.datingfood.backend.api;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    
    @GetMapping("/person/{id}")
    Optional<Person> getById(@PathVariable Long id){
        return personRepository.findById(id);
    }
}
