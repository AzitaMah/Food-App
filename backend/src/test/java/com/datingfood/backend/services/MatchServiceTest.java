package com.datingfood.backend.services;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.MatchRepository;
import com.datingfood.backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MatchServiceTest {

    @Test
    void test_addMatch(){
        // GIVEN
        PersonRepository personRepository = mock(PersonRepository.class);
        MatchRepository matchRepository = mock(MatchRepository.class);
        MatchService matchService = new MatchService(matchRepository,personRepository);
        String username = "tina.smith";
        String partnerUsername = "john.doe";
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null));
        Optional<Person> optionalPartner = Optional.of(new Person("john.doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password", null));

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
        Optional<Person> optionalPartner = Optional.of(new Person("john.doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password", null));

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
        Optional<Person> optionalPerson = Optional.of(new Person("tina.smith","Tina","Smith", "+4912345678", LocalDate.of(2002,1,1), "password",null));

        when(personRepository.findByUsername(username)).thenReturn(optionalPerson);
        when(personRepository.findByUsername(partnerUsername)).thenReturn(Optional.empty());

        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> matchService.addMatch("tina.smith", "john.doe"));
        verify(matchRepository, times(0)).save(any());
    }

}
