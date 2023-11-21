package com.datingfood.backend.api;

import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.repositories.MatchRepository;

@RestController
public class MatchController {
    private final MatchRepository matchRepository;

    MatchController(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
}
