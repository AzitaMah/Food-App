package com.datingfood.backend.services;

import com.datingfood.backend.entities.Match;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.MatchRepository;
import com.datingfood.backend.repositories.PersonRepository;
import com.datingfood.backend.utils.PersonUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PersonRepository personRepository;

    public MatchService(MatchRepository matchRepository, PersonRepository personRepository) {
        this.matchRepository = matchRepository;
        this.personRepository = personRepository;
    }

    /**
     * adds a new entry for the client in the database in table 'Match'
     * @param username username of the client
     * @param partnerUsername username of partner that the client chose
     */
    public void addMatch(final String username, final String partnerUsername) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        Optional<Person> optionalPartner = personRepository.findByUsername(partnerUsername);

        if (optionalPerson.isPresent() && optionalPartner.isPresent()) {
            Person person = optionalPerson.get();
            Person partner = optionalPartner.get();

            Match match = new Match(person, partner);
            matchRepository.save(match);
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * checks the database for entries where the client username is saved in column 'partner_id'
     * and compares them with the clients matches to return a list with matching person
     * @param username username of the client
     * @return List of Persons that all also accepted the client
     */
    public List<Person> getAllAcceptedPartners(final String username) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            List<Person> chosenPartners = matchRepository.findAllMatchesForPerson(person);
            List<Person> personChosen = matchRepository.findPersonAsPartner(person);

            List<Person> acceptedPartners = PersonUtils.findCommonPersons(chosenPartners, personChosen);

            return acceptedPartners;
        } else {
            throw new UsernameNotFoundException("Username does not exist.");
        }

    }
}