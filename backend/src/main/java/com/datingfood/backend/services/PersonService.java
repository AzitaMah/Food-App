package com.datingfood.backend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.datingfood.backend.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final FoodRepository foodRepository;
    private final MatchRepository matchRepository;
    private final static Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    public PersonService(final PersonRepository personRepository, final FoodRepository foodRepository, final MatchRepository matchRepository) {
        this.personRepository = personRepository;
        this.foodRepository = foodRepository;
        this.matchRepository = matchRepository;
    }

    /**
     * receive all information from database from client
     *
     * @param username username of client
     * @return all data from the client
     */
    public Person getPersonByUsername(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            return person;
        } else {
            throw new NoSuchElementException(username + " does not exist");
        }
    }

    /**
     * updates the food choice in the database and clears all old match entries
     *
     * @param username username of client
     * @param foodId   id of food which the client chose
     */
    @Transactional
    public void setFoodChoiceForPerson(final String username, final int foodId) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        final Optional<Food> optionalFood = foodRepository.findFoodById(foodId);
        if (optionalPerson.isPresent() && optionalFood.isPresent()) {
            final Person person = optionalPerson.get();
            final Food food = optionalFood.get();

            person.setFood(food);
            personRepository.save(person);

            matchRepository.deleteAllWherePersonInvolved(person);

        } else {
            throw new NoSuchElementException("Person or food not found");
        }
    }


    /**
     * method for admin only. retrieves a complete list of all person
     *
     * @return List of all persons
     */
    public List<Person> getOverviewForAdmin() {
        final List<Person> personList = personRepository.findAllByOrderByIdAsc();

        return personList;
    }

    /**
     * Method for admin only. Deletes a person and all references of this person in the database
     *
     * @param username username of client to be deleted
     */
    @Transactional
    public void deletePersonEntry(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            matchRepository.deleteAllWherePersonInvolved(person);
            personRepository.deleteByUsername(username);
        }
        else {
            throw new NoSuchElementException(username + " does not exist");
        }
    }
}