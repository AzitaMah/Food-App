package com.datingfood.backend.api;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService personService;

    PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/person/{username}")
    Optional<Person> getByUserName(@PathVariable final String username) {
        return personService.findByUsername(username);
    }

}
