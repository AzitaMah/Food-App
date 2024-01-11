package com.datingfood.backend.api;

import com.datingfood.backend.dto.FoodRequestDTO;
import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    ResponseEntity<String> updateFoodChoice(@PathVariable String username, @RequestBody FoodRequestDTO foodRequestDto) {
        int foodId = foodRequestDto.getFoodId();
        try{
            personService.setFoodChoiceForPerson(username,foodId);

            return ResponseEntity.ok("The food choice was saved successfully for: " + username);
        } catch(NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("person/foodchoice/{username}/{foodId}")
    ResponseEntity<List<UsernameDTO>> getUsersByFoodId(@PathVariable String username, @PathVariable int foodId) {

        List<UsernameDTO> usernameDTOList = personService.getAllUsernamesWithSameFood(username, foodId);
        // TODO currently no error handling because an empty list is not an error
        return ResponseEntity.ok(usernameDTOList);
    }

    @GetMapping("admin/person")
    ResponseEntity<List<Person>> getAllUser() {
        List<Person> personList = personService.getOverviewForAdmin();

        if (personList.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(personList);
    }

}
