package com.datingfood.backend.api;

import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Match;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.PersonRepository;
import com.datingfood.backend.utils.PersonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datingfood.backend.repositories.MatchRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchController {
    private final MatchRepository matchRepository;
    private final PersonRepository personRepository;

    MatchController(MatchRepository matchRepository, PersonRepository personRepository) {
        this.matchRepository = matchRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/match/{username}")
    ResponseEntity<String> setMatch(@PathVariable String username, @RequestBody UsernameDTO usernameDto) {
        Optional<Person> optionalPartner = personRepository.findByUsername(usernameDto.getUsername());
        Optional<Person> optionalPerson = personRepository.findByUsername(username);


        if (optionalPerson.isPresent() && optionalPartner.isPresent()) {
            Person person = optionalPerson.get();
            Person partner = optionalPartner.get();

            Match match = new Match(person, partner);
            matchRepository.save(match);

            return ResponseEntity.ok("Match was created. " + person.getUsername() + "accepted " + partner.getUsername());
        }

        return ResponseEntity.notFound().build();
    }

    //TODO check what needs to be in the responseEntity
    @GetMapping("/match/{username}")
    ResponseEntity<List<Person>> getAllMatches(@PathVariable String username) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            List<Person> chosenPartners = matchRepository.findAllMatchesForPerson(person);
            List<Person> personChosen = matchRepository.findPersonAsPartner(person);

            List<Person> matchPartners = PersonUtils.findCommonPersons(chosenPartners, personChosen);
            return ResponseEntity.ok(matchPartners);
        }
        return ResponseEntity.notFound().build();
    }
}
