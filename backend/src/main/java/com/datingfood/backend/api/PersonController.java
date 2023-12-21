package com.datingfood.backend.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.service.PersonService;

@RestController
public class PersonController {

    private final PersonService personService;

    PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/{username}")
    Optional<Person> getByUserName(@PathVariable final String username) {
        return personService.findByUsername(username);
    }

    @PostMapping(value = "/person/registration", consumes = "application/json")
    public ResponseEntity<String> createPerson(@RequestBody final Person person) {
        final Person optionalPerson = personService.addPerson(person);
        if (optionalPerson == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/person/authentication")
    @ResponseBody
    public String authenticatePerson(@RequestParam(name = "username") final String userName,
            @RequestParam(name = "password") final String password) {
        return personService.authenticatePerson(userName, password);
    }

}
