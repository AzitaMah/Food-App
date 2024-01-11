package com.datingfood.backend.services;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.MatchRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MatchServiceTest {

    @Test
    void test_addMatch(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        MatchRepository matchRepository = mock(MatchRepository.class);
        MatchService matchService = new MatchService(matchRepository,personRepository);
        String username = "tina.smith";
        String partnerUsername = "john.doe";
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null,null));
        Optional<Person> optionalPartner = Optional.of(new Person("john.doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password", null,null));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(personRepository.findByUsername(partnerUsername)).thenReturn(optionalPartner);


        // WHEN
        matchService.addMatch("tina.smith", "john.doe");

        // THEN
        verify(personRepository,times(2)).findByUsername(any());
        verify(matchRepository, times(1)).save(any());

    }

    @Test
    void test_addMatch_no_person(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        MatchRepository matchRepository = mock(MatchRepository.class);
        MatchService matchService = new MatchService(matchRepository,personRepository);
        String username = "tina.smith";
        String partnerUsername = "john.doe";
        Optional<Person> optionalPartner = Optional.of(new Person("john.doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password", null,null));

        when(personRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(personRepository.findByUsername(partnerUsername)).thenReturn(optionalPartner);

        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> matchService.addMatch("tina.smith", "john.doe"));
        verify(matchRepository, times(0)).save(any());
    }

    @Test
    void test_addMatch_no_partner(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        MatchRepository matchRepository = mock(MatchRepository.class);
        MatchService matchService = new MatchService(matchRepository,personRepository);
        String username = "tina.smith";
        String partnerUsername = "john.doe";
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null,null));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(personRepository.findByUsername(partnerUsername)).thenReturn(Optional.empty());

        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> matchService.addMatch("tina.smith", "john.doe"));
        verify(matchRepository, times(0)).save(any());
    }

    @Test
    void test_getAllAcceptedPartners(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        MatchRepository matchRepository = mock(MatchRepository.class);
        MatchService matchService = new MatchService(matchRepository,personRepository);
        String username = "tina.smith";
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null,null));


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));

        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList2.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList2.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(matchRepository.findAllMatchesForPerson(optionalPerson.get())).thenReturn(personList);
        when(matchRepository.findPersonAsPartner(optionalPerson.get())).thenReturn(personList2);

        // WHEN
        List<Person> result = matchService.getAllAcceptedPartners(username);

        // THEN
        assertEquals(3, result.size());
        verify(matchRepository, times(1)).findAllMatchesForPerson(any());
        verify(matchRepository, times(1)).findPersonAsPartner(any());
    }
}
