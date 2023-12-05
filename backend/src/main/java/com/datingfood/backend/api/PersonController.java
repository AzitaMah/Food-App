package com.datingfood.backend.api;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.service.PersonService;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    //private final PersonRepository personRepository;
    private final PersonService personService;


    PersonController(PersonRepository personRepository, PersonService personService) {
        //this.personRepository = personRepository;
        this.personService = personService;
    }



    @GetMapping("/person/{username}")
    Optional<Person> getByUserName(@PathVariable String username) {
        return personService.findByUserName(username);
    }

    @PostMapping(value="/person",consumes = "application/json", produces = "application/json")
     public String addPerson(@RequestBody Person person)
    {
        return personService.addPerson(person);
    }

    @PostMapping(value = "/person/authentication")
    @ResponseBody
    public String authenticatePerson(@RequestParam(name = "username") String  userName ,@RequestParam(name = "password") String password){
    //public String authenticatePerson(@RequestBody Person person){
        return personService.authenticatePerson(userName, password);
    }



}
