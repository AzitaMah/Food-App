package com.datingfood.backend.api;

import com.datingfood.backend.entities.Candidate;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.CandidateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// braucht man diesen controller oder reicht einer für matches only?
@RestController
public class CandidateController {

    private final CandidateRepository candidateRepository;

    CandidateController(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/candidate")
    List<Candidate> all() {
        return candidateRepository.findAll();
    }

    @GetMapping("/candidate")
    // parameter userId and foodId returning all users with the same food id -> matches
    public Person getById(@RequestParam(value="userId") Long id){
        return candidateRepository.findById(id);
    }


    @PostMapping("/candidate")
    // parameter userId and foodId
}
