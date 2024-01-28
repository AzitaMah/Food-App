package com.datingfood.backend.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.dto.ContactDTO;
import com.datingfood.backend.dto.PersonInfoDTO;
import com.datingfood.backend.dto.UsernameDTO;
import com.datingfood.backend.services.MatchService;

@RestController
@RequestMapping("/api")
public class MatchController {

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;

    @Autowired
    MatchController(final MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("match/selection/{username}")
    ResponseEntity<List<PersonInfoDTO>> getUsersByFoodId(@PathVariable final String username) {
        try {
            final List<PersonInfoDTO> usernameDTOList = matchService.getAllPersonInfoWithSameFood(username);
            return ResponseEntity.ok(usernameDTOList);
        } catch (NoSuchElementException exception) {
            logger.warn("User with username '{}' not found", username, exception);
            return ResponseEntity.notFound().build();
        } catch (RuntimeException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/match/{username}")
    ResponseEntity<Void> setMatch(@PathVariable String username, @RequestBody UsernameDTO usernameDto) {
        final String partnerUsername = usernameDto.getUsername();

        try {
            matchService.addMatch(username, partnerUsername);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            logger.warn("User with username '{}' or '{}' not found", username, partnerUsername, exception);
            return ResponseEntity.notFound().build();
        }catch (RuntimeException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/match/{username}")
    public ResponseEntity<Map<String, List<?>>> getAllPartnersAndMatches(@PathVariable String username) {
        try {
            final List<ContactDTO> matches = matchService.getAllAcceptedPartners(username);
            final List<PersonInfoDTO> incompleteMatches = matchService.getAllIncompleteMatches(username);

            Map<String, List<?>> allMatchInfo = new HashMap<>();
            allMatchInfo.put("complete", matches);
            allMatchInfo.put("incomplete", incompleteMatches);

            return ResponseEntity.ok(allMatchInfo);
        } catch (NoSuchElementException exception) {
            logger.error("Error while retrieving partners and matches for user '{}'", username, exception);
            return ResponseEntity.notFound().build();
        } catch (RuntimeException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

}
