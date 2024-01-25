import React, { FC, useState } from 'react';
import styles from './FoodSwipe.module.css';
import { Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Stack, Typography } from "@mui/material";
import NotInterestedIcon from "@mui/icons-material/NotInterested";
import FavoriteIcon from "@mui/icons-material/Favorite";

interface FoodSwipeProps {}

const foodOptions: string[] = ["Sushi", "Burger", "Pizza", "Ramen", "Meatballs"];

const FoodSwipe: FC<FoodSwipeProps> = () => {
    const [currentIndex, setCurrentIndex] = useState(0);

    const handleDislike = () => {
        setCurrentIndex(currentIndex + 1);
        if (currentIndex + 1 >= foodOptions.length) {
            setCurrentIndex(0);
        }
        console.log(foodOptions[currentIndex]);
    };

    const currentFood = foodOptions[currentIndex];
    //const imagePath = `../../assets/${currentFood.toLowerCase()}.jpg`;

    return (
        <Stack direction="row" justifyContent="center" sx={{ p: "20px" }}>
            <div className={styles.FoodSwipe} data-testid="FoodSwipe">
                <Card sx={{ maxWidth: 345 }}>
                    <CardActionArea>
                        <CardMedia
                            component="img"
                            height="140"
                            src={`${process.env.PUBLIC_URL}/${currentFood.toLowerCase()}.jpg`}
                            alt={currentFood}
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                {currentFood}
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                    <CardActions>
                        <Stack direction="row" justifyContent="space-between" flex="content">
                            <Button size="small" color="primary" onClick={handleDislike}>
                                <NotInterestedIcon />
                            </Button>
                            <Button size="small" color="primary" href="/swipe-list">
                                <FavoriteIcon />
                            </Button>
                        </Stack>
                    </CardActions>
                </Card>
            </div>
        </Stack>
    );
};

export default FoodSwipe;
