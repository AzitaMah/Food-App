package com.datingfood.backend.service;

import com.datingfood.backend.repositories.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.exception.PersonNotFoundException;

import java.util.Optional;

@Service
public class PersonService {


    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }



    /**
     *
     * @param userName of questioned Person
     * @param password of questioned Person
     * @return If the given person is correspondent to the person-userName in database the right or wrong input of the Password will be returned
     * @throws PersonNotFoundException when given Person has no matching Person id in the db
     */
    public String authenticatePerson(String userName, String password) throws PersonNotFoundException
    {
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(); //makes hashed password

        Optional<Person> opPerson = personRepository.findByUserName(userName);
        if(opPerson.isPresent()){ //check if person is in dB
            Person dbPerson =opPerson.get(); // get actual person in dB
            if(bcrypt.matches(password,dbPerson.getPassword())) //check if hashed input password matches with hashed pwd in db
            {return "Authenticated Person";}
            else{return "Incorrect Password";} //TODO:return new ResponseEntity<>(foodSelection, HttpStatus.OK); error code
        }throw new PersonNotFoundException("No Person found for this Person-Id!"); //TODO:return new ResponseEntity<>(foodSelection, HttpStatus.OK); error code
    }

    public String addPerson(Person person){
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(); //makes hashed password
        String encryptedPwd=bcrypt.encode(person.getPassword()); //hashed password
        person.setPassword(encryptedPwd); //set hashed pwd before saving person into the db

        Person savePerson = personRepository.save(person);
        return savePerson.getLastName()+" with ID "+savePerson.getId()+"added successfully to database";
    }

    public Optional<Person> findByUserName(String userName){
       return  personRepository.findByUserName(userName);

    }


}
