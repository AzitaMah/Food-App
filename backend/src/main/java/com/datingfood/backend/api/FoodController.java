package com.datingfood.backend.api;

import com.datingfood.backend.dto.FoodResponseDTO;
import com.datingfood.backend.services.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {

    private final FoodService foodService;
    private final Logger logger = LoggerFactory.getLogger(FoodController.class);

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("food")
    public ResponseEntity<List<FoodResponseDTO>> getRandomFood() {
        try {
            List<FoodResponseDTO> foodResponseDTOList = foodService.getFoodSelection();
            return ResponseEntity.ok(foodResponseDTOList);
        } catch (RuntimeException exception) {
            logger.error("Error while getting random food list", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
