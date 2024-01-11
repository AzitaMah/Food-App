package com.datingfood.backend.api;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("person/{username}")
    Optional<Person> getByUserName(@PathVariable final String username) {
        return personRepository.findByUsername(username);
    }

    @GetMapping("admin/person")
    List<Person> getAllUser() {
        return personRepository.findAll();
    }

}
