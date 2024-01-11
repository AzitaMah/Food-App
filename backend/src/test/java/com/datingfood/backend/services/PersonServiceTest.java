package com.datingfood.backend.services;

import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.FoodRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

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
        Optional<Food> optionalFood = Optional.of(new Food(1,"Pizza"));

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
        Optional<Food> optionalFood = Optional.of(new Food(1,"Pizza"));

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

    @Test
    void test_getAllUsernamesWithSameFood(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        FoodRepository foodRepository = mock(FoodRepository.class);
        PersonService personService = new PersonService(personRepository, foodRepository);
        String username = "john_doe";
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1",null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3",null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4",null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password",null,null));

        List<UsernameDTO> expectedList = Stream.of("alice_smith", "michael_j", "emily_w", "admin")
                .map(UsernameDTO::new)
                .toList();
        when(personRepository.findAllByFood_Id(0)).thenReturn(personList);

        // WHEN
        List<UsernameDTO> actualList = personService.getAllUsernamesWithSameFood(username,0);

        // THEN
        assertEquals(expectedList,actualList);
    }

}
