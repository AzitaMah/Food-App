package com.datingfood.backend.api;

import java.util.List;

import com.datingfood.backend.dto.FoodResponseDTO;
import com.datingfood.backend.entities.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.logging.LoggingController;
import com.datingfood.backend.repositories.FoodRepository;

@RestController
@RequestMapping("/api")
public class FoodController {

    private final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    private final FoodRepository foodRepository;

    FoodController(final FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("food")
    public ResponseEntity<List<FoodResponseDTO>> getRandomFood() {
        final List<Food> foodSelection = foodRepository.findFiveRandomFood();

        if (!foodSelection.isEmpty()) {
            logger.info("Received food selection from database");

            List<FoodResponseDTO> foodResponseDTOS = foodSelection
                    .stream()
                    .map(food -> new FoodResponseDTO(food.getId(),food.getName()))
                    .toList();

            return new ResponseEntity<>(foodResponseDTOS, HttpStatus.OK);
        }
        logger.error("Couldn't fetch food selection from database");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
