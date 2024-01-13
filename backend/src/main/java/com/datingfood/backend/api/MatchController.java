package com.datingfood.backend.api;

import com.datingfood.backend.dto.ContactDTO;
import com.datingfood.backend.dto.PersonInfoDTO;
import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.services.MatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class MatchController {
    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;

    @Autowired
    MatchController(final MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("match/selection/{username}/{foodId}")
    ResponseEntity<List<PersonInfoDTO>> getUsersByFoodId(@PathVariable final String username, @PathVariable final int foodId) {

        final List<PersonInfoDTO> usernameDTOList = matchService.getAllUsernamesWithSameFood(username, foodId);

        return ResponseEntity.ok(usernameDTOList);
    }

    @PostMapping("/match/{username}")
    ResponseEntity<String> setMatch(@PathVariable String username, @RequestBody UsernameDTO usernameDto) {
        final String partnerUsername = usernameDto.getUsername();

        try {
            matchService.addMatch(username, partnerUsername);

            return ResponseEntity.ok("Match was created. " + username + "accepted " + partnerUsername);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO check what needs to be in the responseEntity
    @GetMapping("/match/{username}")
    public ResponseEntity<Map<String,List<?>>> getAllPartnersAndMatches(@PathVariable String username) {
        try {
            final List<ContactDTO> matches = matchService.getAllAcceptedPartners(username);
            final List<PersonInfoDTO> incompletedMatches = matchService.getAllIncompleteMatches(username);

            Map<String, List<?>> allMatchInfo = new HashMap<>();
            allMatchInfo.put("complete", matches);
            allMatchInfo.put("incomplete", incompletedMatches);

            return ResponseEntity.ok(allMatchInfo);
        } catch (UsernameNotFoundException exception) {
            logger.error(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
