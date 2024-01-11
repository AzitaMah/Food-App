package com.datingfood.backend.repositories;

import com.datingfood.backend.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT f.name FROM Food f ORDER BY RANDOM() LIMIT 5")
    List<String> findFiveRandomFood();

}
