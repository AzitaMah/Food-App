package com.datingfood.backend.services;

import com.datingfood.backend.api.FoodController;
import com.datingfood.backend.dto.FoodResponseDTO;
import com.datingfood.backend.entities.Food;
import com.datingfood.backend.repositories.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final Logger logger = LoggerFactory.getLogger(FoodService.class);

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodResponseDTO> getFoodSelection() {
        final List<Food> foodSelection = foodRepository.findFiveRandomFood();

        if (!foodSelection.isEmpty()) {
            logger.info("Received food selection from database");

            final List<FoodResponseDTO> foodResponseDTOList = foodSelection
                    .stream()
                    .map(food -> new FoodResponseDTO(food.getId(), food.getName(), food.getImageBase64()))
                    .toList();
            return foodResponseDTOList;
        }
        throw new RuntimeException();
    }
}