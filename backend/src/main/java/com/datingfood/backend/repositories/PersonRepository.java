package com.datingfood.backend.repositories;

import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<Person> findAllByFood_Id(int foodId);

    List<Person> findAllByOrderByIdAsc();

}
