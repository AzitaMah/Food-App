package com.datingfood.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datingfood.backend.entities.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {


}
