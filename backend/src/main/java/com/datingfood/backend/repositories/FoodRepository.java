package com.datingfood.backend.repositories;

import com.datingfood.backend.entities.Food;
import com.datingfood.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("SELECT f FROM Food f ORDER BY RANDOM() LIMIT 5")
    List<Food> findFiveRandomFood();

    Optional<Food> findFoodById(int foodId);
}
