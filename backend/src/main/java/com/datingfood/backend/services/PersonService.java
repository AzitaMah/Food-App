package com.datingfood.backend.services;

import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository, final FoodRepository foodRepository) {
        this.personRepository = personRepository;
        this.foodRepository = foodRepository;
    }

    /**
     * receive all information from database from client
     * @param username username of client
     * @return all data from the client
     */
    public Person getPersonByUsername(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            return person;
        }
        throw new NoSuchElementException(username + " does not exist");
    }

    /**
     * updates the food choice in the database
     * @param username username of client
     * @param foodId id of food which the client chose
     */
    public void setFoodChoiceForPerson(final String username, final int foodId) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        final Optional<Food> optionalFood = foodRepository.findFoodById(foodId);
        if (optionalPerson.isPresent() && optionalFood.isPresent()) {
            final Person person = optionalPerson.get();
            final Food food = optionalFood.get();

            person.setFood(food);
            personRepository.save(person);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * retrieves a List of usernames from the database where the food choice is the same as the clients food choice
     * @param username username of client
     * @param foodId id of food which the client chose
     * @return List with usernames of persons who have the same food choice
     */
    public List<UsernameDTO> getAllUsernamesWithSameFood(final String username, final int foodId) {
        final List<Person> personList = personRepository.findAllByFood_Id(foodId);

        final List<UsernameDTO> usernameDTOSList = personList
                .stream()
                .map(person ->
                        new UsernameDTO(person.getUsername()))
                .filter(person -> !person.getUsername().equals(username))
                .toList();

        return usernameDTOSList;
    }

    /**
     * method for admin only. retrieves a complete list of all person
     * @return List of all persons
     */
    public List<Person> getOverviewForAdmin(){
        final List<Person> personList = personRepository.findAllByOrderByIdAsc();

        return personList;
    }
}
