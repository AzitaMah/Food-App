package com.datingfood.backend.repositories;

import java.util.List;

import com.datingfood.backend.entities.Candidate;
import com.datingfood.backend.entities.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    //@Query("SELECT p FROM Person p WHERE p.id = :foodId")
    //List<Person> findById(@Param("columnValue") String columnValue) {
    //    return null;
    //}

}
