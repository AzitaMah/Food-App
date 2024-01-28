package com.datingfood.backend.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datingfood.backend.dto.FoodRequestDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.services.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("person/{username}")
    ResponseEntity<Person> getByUsername(@PathVariable final String username) {
        try {
            final Person person = personService.getPersonByUsername(username);

            return ResponseEntity.ok(person);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("person/{username}")
    ResponseEntity<Void> updateFoodChoice(@PathVariable String username, @RequestBody FoodRequestDTO foodRequestDto) {
        final int foodId = foodRequestDto.getFoodId();
        try {
            personService.setFoodChoiceForPerson(username, foodId);

            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("admin/person")
    ResponseEntity<List<Person>> getAllUser() {
        List<Person> personList = personService.getOverviewForAdmin();

        if (personList.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(personList);
    }

    @DeleteMapping("admin/person/{username}")
    ResponseEntity<Void> deletePerson(@PathVariable String username) {
        try {
            personService.deletePersonEntry(username);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
