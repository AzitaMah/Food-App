package com.datingfood.backend.api;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.entities.Candidate;
import com.datingfood.backend.repositories.CandidateRepository;

@RestController
public class CandidateController {

    private final CandidateRepository candidateRepository;

    CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    //@GetMapping("/candidate/{foodId}")
    //// parameter userId and foodId returning all users with the same food id -> matches
    //public Optional<Candidate> getById(@PathVariable final Long foodId) {
    //    return candidateRepository.findAll;
    //}

    // parameter userId and foodId

}
