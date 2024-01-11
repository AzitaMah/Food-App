package com.datingfood.backend.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;

class PersonServiceTest {

    @Test
    void test_getPersonByUsername(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "tina.smith";
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null,null));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);

        // WHEN
        personService.getPersonByUsername(username);

        // THEN
        verify(personRepository, times(1)).findByUsername(username);
    }

    @Test
    void test_getPersonByUsername_person_not_existing(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "person.not.existing";
        Optional<Person> optionalPerson = Optional.empty();

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);

        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> personService.getPersonByUsername(username));
    }

    @Test
    void test_setFoodChoiceForPerson(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "test.user";
        int foodId = 1;
        Optional<Person> optionalPerson = Optional.of(new Person("test.user","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password", null,null));
        Optional<Food> optionalFood = Optional.of(new Food(1,"Pizza","dummyBase64"));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(foodRepository.findFoodById(foodId)).thenReturn(optionalFood);


        // WHEN
        personService.setFoodChoiceForPerson(username, foodId);

        // THEN
        verify(personRepository, times(1)).findByUsername(username);
        verify(foodRepository, times(1)).findFoodById(foodId);
        verify(personRepository, times(1)).save(any());
    }

    @Test
    void test_setFoodChoiceForPerson_no_person(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "test.user";
        int foodId = 1;
        Optional<Person> optionalPerson = Optional.empty();
        Optional<Food> optionalFood = Optional.of(new Food(1,"Pizza","dummyBase64"));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(foodRepository.findFoodById(foodId)).thenReturn(optionalFood);


        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> personService.setFoodChoiceForPerson(username, foodId));
        verify(personRepository, times(1)).findByUsername(username);
        verify(foodRepository, times(1)).findFoodById(foodId);
        verify(personRepository, times(0)).save(any());
    }

    @Test
    void test_setFoodChoiceForPerson_no_food(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "test.user";
        int foodId = 1;
        Optional<Person> optionalPerson = Optional.of(new Person("test.user","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password", null,null));
        Optional<Food> optionalFood = Optional.empty();

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(foodRepository.findFoodById(foodId)).thenReturn(optionalFood);


        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> personService.setFoodChoiceForPerson(username, foodId));
        verify(personRepository, times(1)).findByUsername(username);
        verify(foodRepository, times(1)).findFoodById(foodId);
        verify(personRepository, times(0)).save(any());
    }


}
