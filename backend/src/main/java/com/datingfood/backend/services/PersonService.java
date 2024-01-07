package com.datingfood.backend.services;

import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private FoodRepository foodRepository;

    public PersonService(final PersonRepository personRepository, FoodRepository foodRepository) {
        this.personRepository = personRepository;
        this.foodRepository = foodRepository;
    }

    public Person getPersonByUsername(final String username) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            return person;
        }
        throw new UsernameNotFoundException(username + " does not exist");
    }


    public void setFoodChoiceForPerson(final String username, final int foodId) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        Optional<Food> optionalFood = foodRepository.findFoodById(foodId);
        if (optionalPerson.isPresent() && optionalFood.isPresent()) {
            Person person = optionalPerson.get();
            Food food = optionalFood.get();

            person.setFood(food);
            personRepository.save(person);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<UsernameDTO> getAllUsernamesWithSameFood(final String username, final int foodId) {
        List<Person> personList = personRepository.findAllByFood_Id(foodId);

        List<UsernameDTO> usernameDTOSList = personList
                .stream()
                .map(person ->
                        new UsernameDTO(person.getUsername()))
                .filter(person -> !person.getUsername().equals(username))
                .toList();

        return usernameDTOSList;
    }

    public List<Person> getOverviewForAdmin(){
        List<Person> personList = personRepository.findAllByOrderByIdAsc();

        return personList;
    }
}
