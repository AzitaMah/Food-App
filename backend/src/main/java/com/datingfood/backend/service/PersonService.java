package com.datingfood.backend.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.entities.Role;
import com.datingfood.backend.exception.PersonNotFoundException;
import com.datingfood.backend.repositories.PersonRepository;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);
    PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @param userName of questioned Person
     * @param password of questioned Person
     * @return If the given person is correspondent to the person-userName in database the right or wrong input of the Password will be returned
     * @throws PersonNotFoundException when given Person has no matching Person id in the db
     */
    public String authenticatePerson(final String userName, final String password) throws PersonNotFoundException {
        final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); //makes hashed password

        final Optional<Person> opPerson = personRepository.findByUserName(userName);
        if (opPerson.isPresent()) { //check if person is in dB
            final Person dbPerson = opPerson.get(); // get actual person in dB
            if (bcrypt.matches(
                    password,
                    dbPerson.getPassword())) //check if hashed input password matches with hashed pwd in db
            {
                return "Authenticated Person";
            } else {
                return "Incorrect Password";
            } //TODO:return new ResponseEntity<>(foodSelection, HttpStatus.OK); error code
        }
        throw new PersonNotFoundException("No Person found for this Person-Id!"); //TODO:return new ResponseEntity<>(foodSelection, HttpStatus.OK); error code
    }

    public Person addPerson(final Person person) {
        final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); //makes hashed password
        final String encryptedPwd = bcrypt.encode(person.getPassword()); //hashed password
        person.setPassword(encryptedPwd); //set hashed pwd before saving person into the db
        person.setRole(Role.USR);
        try {
            return personRepository.save(person);
        }catch(final DataIntegrityViolationException exception){
            logger.error(exception.getMessage());
            return null;
        }
    }

    public Optional<Person> findByUserName(final String userName) {
        return personRepository.findByUserName(userName);
    }

}
