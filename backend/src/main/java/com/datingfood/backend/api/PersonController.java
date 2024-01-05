package com.datingfood.backend.api;

import com.datingfood.backend.dto.FoodRequestDto;
import com.datingfood.backend.dto.UsernameDto;
import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;
    private final FoodRepository foodRepository;

    PersonController(PersonRepository personRepository, FoodRepository foodRepository) {
        this.personRepository = personRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping("person/{username}")
    ResponseEntity<Person> getByUsername(@PathVariable final String username) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("person/{username}")
    ResponseEntity<String> setFoodChoice(@PathVariable String username, @RequestBody FoodRequestDto foodRequestDto) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        Optional<Food> optionalFood = foodRepository.findFoodById(foodRequestDto.getFoodId());

        if (optionalPerson.isPresent() && optionalFood.isPresent()) {
            Person person = optionalPerson.get();
            Food food = optionalFood.get();

            person.setFood(food);
            personRepository.save(person);

            return ResponseEntity.ok("The food choice was saved successfully for: " + username);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("person/foodchoice/{foodId}")
    ResponseEntity<List<UsernameDto>> getUsersByFoodId(@PathVariable int foodId) {

        List<Person> personList = personRepository.findAllByFood_Id(foodId);

        if (!personList.isEmpty()) {
            List<UsernameDto> usernameDtos = personList
                    .stream()
                    .map(person -> new UsernameDto(person.getUsername()))
                    .toList();

            return ResponseEntity.ok(usernameDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("admin/person")
    ResponseEntity<List<Person>> getAllUser() {
        List<Person> personList = personRepository.findAll();

        if(personList.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(personList);
    }

}
