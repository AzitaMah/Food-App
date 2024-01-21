package com.datingfood.backend.repositories;

import com.datingfood.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import com.datingfood.backend.entities.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m.partner FROM Match m WHERE m.person = :person")
    List<Person> findAllPartnersOfPerson(@Param("person") Person person);

    @Query("SELECT m.person FROM Match m WHERE m.partner = :person")
    List<Person> findPersonAsPartner(Person person);

    @Query("SELECT m FROM Match m WHERE m.person = :person")
    List<Match> findAllMatchesForPerson(@Param("person") Person person);


}
