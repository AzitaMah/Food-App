package com.datingfood.backend.repositories;

import com.datingfood.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import com.datingfood.backend.entities.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m.partner FROM Match m WHERE m.person = :person")
    List<Person> findAllMatchesForPerson(@Param("person") Person person);

    @Query("SELECT CASE WHEN :person IN (m.person.id, m.partner.id) THEN " +
            "CASE WHEN m.person.id = :person THEN m.partner ELSE m.person END END " +
            "FROM Match m " +
            "WHERE :person IN (m.person.id, m.partner.id) AND (m.person.id IN :partnerList OR m.partner.id IN :partnerList)")
    List<Person> findCorrespondingPersons(@Param("person") Long person, @Param("partnerList") List<Long> partnerList);

    @Query("SELECT m.person FROM Match m WHERE m.partner = :person")
    List<Person> findPersonAsPartner(Person person);


}
