package com.datingfood.backend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datingfood.backend.dto.ContactDTO;
import com.datingfood.backend.dto.PersonInfoDTO;
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
     *
     * @param username        username of the client
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
            logger.error("Failed to add match. Usernames {} or {} not found.", username, partnerUsername);
            throw new NoSuchElementException("User not found");
        }
    }

    /**
     * checks the database for entries where the client username is saved in column 'partner_id'
     * and compares them with the clients matches to return a list with matching person
     *
     * @param username username of the client
     * @return List of Persons with contact information that all also accepted the client
     */
    public List<ContactDTO> getAllAcceptedPartners(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);

            if (optionalPerson.isPresent()) {
                final Person person = optionalPerson.get();

                final List<Person> chosenPartners = matchRepository.findAllPartnersOfPerson(person);
                final List<Person> personChosen = matchRepository.findPersonAsPartner(person);

                final List<ContactDTO> acceptedPartners =
                        MatchUtils.createPersonContactDTOList(MatchUtils.findCommonPersons(chosenPartners, personChosen));

                return acceptedPartners;
            }else {
                throw new NoSuchElementException("Username '" + username + "' does not exist");
            }
    }

    /**
     * finds all matches for client that are still incomplete because the corresponding partner did not 'accept'
     *
     * @param username username of client
     * @return List with all incomplete matches
     */
    public List<PersonInfoDTO> getAllIncompleteMatches(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
        if (optionalPerson.isPresent()) {
            final Person person = optionalPerson.get();

            final List<Person> chosenPartners = matchRepository.findAllPartnersOfPerson(person);
            final List<Person> personChosen = matchRepository.findPersonAsPartner(person);

            final List<PersonInfoDTO> incompleteMatches = MatchUtils.createPersonInfoDTOList(
                    MatchUtils.findDifferentPersons(chosenPartners, personChosen));

            return incompleteMatches;
        } else {
            throw new NoSuchElementException("Username does not exist");
        }
    }

    /**
     * retrieves a List of usernames from the database where the food choice is the same as the clients food choice
     *
     * @param username username of client
     * @return List with usernames of persons who have the same food choice
     */
    public List<PersonInfoDTO> getAllPersonInfoWithSameFood(final String username) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);
            if (optionalPerson.isPresent()) {
                final Person person = optionalPerson.get();
                final List<Person> personList = personRepository.findAllByFood_Id(person.getFood().getId());

                final List<Person> personSelectionList = getPossiblePartners(username, personList);

                final List<PersonInfoDTO> personInfoDTOList = personSelectionList
                        .stream()
                        .map(p ->
                                new PersonInfoDTO(p.getUsername(), p.getProfileImage(), p.getBirthDate()))
                        .filter(p -> !p.getUsername().equals(username))
                        .toList();

                return personInfoDTOList;
            }
           else{ logger.error("Failed to retrieve persons with the same food choice ");
            throw new NoSuchElementException();
           }
    }

    /**
     * Gets a list of possible partners for a given user by finding persons with the same food choice
     * who are not already in a match with the user.
     *
     * @param username   The username of the user for whom to find possible partners.
     * @param personList The list of persons with the same food choice.
     * @return List of persons who are possible partners for the user.
     * @throws NoSuchElementException If the user with the given username is not found in the repository.
     */
    private List<Person> getPossiblePartners(final String username, final List<Person> personList) {
        final Optional<Person> optionalPerson = personRepository.findByUsername(username);

            if (optionalPerson.isPresent()) {
                final Person person = optionalPerson.get();

                final List<Person> partnerInMatch = matchRepository.findAllPartnersOfPerson(person);

                final List<Person> possiblePartner = MatchUtils.findDifferentPersons(personList, partnerInMatch);

                return possiblePartner;
            }
           else {
                throw new NoSuchElementException("Person with " + username + " can't be found");
            }
    }

}
