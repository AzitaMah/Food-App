package com.datingfood.backend.api;

import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.services.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class MatchController {

    private final MatchService matchService;

    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/match/{username}")
    ResponseEntity<String> setMatch(@PathVariable String username, @RequestBody UsernameDTO usernameDto) {
        String partnerUsername = usernameDto.getUsername();

        try {
            matchService.addMatch(username, partnerUsername);

            return ResponseEntity.ok("Match was created. " + username + "accepted " + partnerUsername);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO check what needs to be in the responseEntity
    @GetMapping("/match/{username}")
    ResponseEntity<List<Person>> getAllMatches(@PathVariable String username) {
        try {
            List<Person> acceptedPartners = matchService.getAllAcceptedPartners(username);

            return ResponseEntity.ok(acceptedPartners);
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
