package com.datingfood.backend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.datingfood.backend.dto.ContactDTO;
import com.datingfood.backend.dto.PersonInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datingfood.backend.entities.Match;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.MatchRepository;
import com.datingfood.backend.repositories.PersonRepository;
import com.datingfood.backend.utils.MatchUtils;

@Service
public class MatchService {
    private final Logger logger = LoggerFactory.getLogger(MatchService.class);
    private final MatchRepository matchRepository;
    private final PersonRepository personRepository;

    @Autowired
    public MatchService(final MatchRepository matchRepository, final PersonRepository personRepository) {
        this.matchRepository = matchRepository;
        this.personRepository = personRepository;
    }

    /**
     * adds a new entry for the client in the database in table 'Match'
     * @param username username of the client
     * @param partnerUsername username of partner that the client chose
     */
    public void addMatch(final String username, final String partnerUsername) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        final Optional<Person> optionalPartner = personRepository.findByUsername(partnerUsername);

        if (optionalPerson.isPresent() && optionalPartner.isPresent()) {
            final Person person = optionalPerson.get();
            final Person partner = optionalPartner.get();

            final Match match = new Match(person, partner);
            matchRepository.save(match);
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * checks the database for entries where the client username is saved in column 'partner_id'
     * and compares them with the clients matches to return a list with matching person
     * @param username username of the client
     * @return List of Persons with contact information that all also accepted the client
     */
    public List<ContactDTO> getAllAcceptedPartners(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            final Person person = optionalPerson.get();

            final List<Person> chosenPartners = matchRepository.findAllMatchesForPerson(person);
            final List<Person> personChosen = matchRepository.findPersonAsPartner(person);

            final List<ContactDTO> acceptedPartners = MatchUtils.createPersonContactDTOList(MatchUtils.findCommonPersons(chosenPartners, personChosen));

            return acceptedPartners;
        } else {
            throw new NoSuchElementException("Username does not exist.");
        }

    }

    /**
     * finds all matches for client that are still incomplete because the corresponding partner did not 'accept'
     * @param username username of client
     * @return List with all incomplete matches
     */
    public List<PersonInfoDTO> getAllIncompleteMatches(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            final Person person = optionalPerson.get();

            final List<Person> chosenPartners = matchRepository.findAllMatchesForPerson(person);
            final List<Person> personChosen = matchRepository.findPersonAsPartner(person);

            final List<PersonInfoDTO> incompleteMatches = MatchUtils.createPersonInfoDTOList(
                    MatchUtils.findDifferentPersons(chosenPartners, personChosen));

            return incompleteMatches;
        }
        else {
            throw new NoSuchElementException("Username does not exist.");
        }
    }

    /**
     * retrieves a List of usernames from the database where the food choice is the same as the clients food choice
     * @param username username of client
     * @param foodId id of food which the client chose
     * @return List with usernames of persons who have the same food choice
     */
    public List<PersonInfoDTO> getAllUsernamesWithSameFood(final String username, final int foodId) {
        final List<Person> personList = personRepository.findAllByFood_Id(foodId);

        final List<Person> personSelectionList = getPossiblePartners(username, personList);

        final List<PersonInfoDTO> personInfoDTOList = personSelectionList
                .stream()
                .map(person ->
                        new PersonInfoDTO(person.getUsername(),person.getProfileImage(),person.getBirthDate()))
                .filter(person -> !person.getUsername().equals(username))
                .toList();

        return personInfoDTOList;
    }

    private List<Person> getPossiblePartners(final String username, final List<Person> personList){
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            final Person person = optionalPerson.get();

            final List<Person> partnerInMatch = matchRepository.findAllMatchesForPerson(person);

            final List<Person> possiblePartner = MatchUtils.findDifferentPersons(personList, partnerInMatch);

            return possiblePartner;
        }
        throw new NoSuchElementException("Person with " + username + " can't be found");
    }

}
