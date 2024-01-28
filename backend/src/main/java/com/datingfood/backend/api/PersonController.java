package com.datingfood.backend.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datingfood.backend.dto.FoodRequestDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.services.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

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
            logger.error("Person with username '{}' not found", username, exception);
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
            logger.error("Peron with username '{}' or foodId '{}'  not found to update food choice",username,foodId,exception);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("admin/person")
    ResponseEntity<List<Person>> getAllUser() {
        try {
            List<Person> personList = personService.getOverviewForAdmin();

            if (personList.isEmpty()) {
                logger.warn("No users found when retrieving all users for admin");
                return ResponseEntity.internalServerError().build();
            }
            logger.info("List of all users generated for administration purposes");
            return ResponseEntity.ok(personList);
        }catch (Exception exception){
            logger.error("Error while retrieving all users for admin", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("admin/person/{username}")
    ResponseEntity<Void> deletePerson(@PathVariable String username) {
        try {
            personService.deletePersonEntry(username);
            logger.info("Deleted person entry for username '{}' ", username);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            logger.error("Person with username '{}' not found while trying to delete", username, exception);
            return ResponseEntity.notFound().build();
        }
    }
}
