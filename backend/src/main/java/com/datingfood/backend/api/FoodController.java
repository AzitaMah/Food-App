package com.datingfood.backend.api;

import com.datingfood.backend.logging.LoggingController;
import com.datingfood.backend.repositories.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {
    private final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    private final FoodRepository foodRepository;

    FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("/food")
    public ResponseEntity<List<String>> getRandomFood() {
        List<String> foodSelection = foodRepository.findFiveRandomFood();

        if (foodSelection.isEmpty()) {
            logger.error("Couldn't fetch food selection from database");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Received food selection from database");
        return new ResponseEntity<>(foodSelection, HttpStatus.OK);
    }

}
