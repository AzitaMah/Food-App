import React, {useEffect, useState} from 'react';
import FoodCard from './FoodCard';
import {useLocation} from "react-router-dom";
import {BottomNavigation, BottomNavigationAction, Box, CssBaseline, Paper} from "@mui/material";
import SwipeIcon from "@mui/icons-material/Swipe";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";


const FoodScreen: React.FC = () => {
    // Raw code Foods since not connected yet to the database

    const initialFoods = [
        {id: 1, name: 'Pizza', description: 'Delicious pizza with various toppings', imageUrl: '../assets/pizza.jpg'},
        {id: 2, name: 'Burger', description: 'Classic burger with a juicy patty', imageUrl: '../assets/burger.jpg'},
        {id: 3, name: 'Sushi', description: 'Fresh and tasty sushi rolls', imageUrl: '../assets/sushi.jpg'},

    ];


    const [foods, setFoods] = useState(initialFoods);


    const handleSwipe = (id: number, direction: 'left' | 'right') => {
        // Handle swipe action remove food from the list, update preferences, etc.
        console.log(`Swiped ${direction} on food with ID ${id}`);
    };

    function FixedBottomNavigation() {
        const location = useLocation();
        const [value, setValue] = useState(0);

        // Effekt, um den Zustand basierend auf der aktuellen URL zu aktualisieren
        useEffect(() => {
            const path: string = location.pathname;
            //to highlight correct bottom navigation icon
            if (path === '/') {
                setValue(0);
            } else if (path === '/matches') {
                setValue(1);
            } else {
                setValue(2);
            }
        }, [location.pathname]);

        return (
            <Box sx={{ pb: 7 }}>
                <CssBaseline />
                <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>
                    <BottomNavigation
                        showLabels
                        value={value}
                        onChange={(event, newValue) => {
                            setValue(newValue);
                        }}
                    >
                        <BottomNavigationAction label="Swipe" href="/" icon={<SwipeIcon />} />
                        <BottomNavigationAction label="Matches" href="/matches" icon={<FavoriteBorderIcon />} />
                    </BottomNavigation>
                </Paper>
            </Box>
        );
    }

    return (
        <div>
            <h1>Food Swipe App</h1>
            <div>
                {foods.map(food => (
                    <FoodCard
                        key={food.id}
                        id={food.id}
                        name={food.name}
                        description={food.description}
                        imageUrl={food.imageUrl}
                        onSwipe={handleSwipe}
                    />
                ))}
            </div>
            <FixedBottomNavigation></FixedBottomNavigation>
        </div>
    );
}


export default FoodScreen;