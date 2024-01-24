import React, { FC } from 'react';
import styles from './FoodSwipe.module.css';
import {Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Stack, Typography} from "@mui/material";
import NotInterestedIcon from "@mui/icons-material/NotInterested";
import FavoriteIcon from "@mui/icons-material/Favorite";

interface FoodSwipeProps {}

const foodOptions: string[] = ["Sushi", "Burger", "Pizza", "Pasta", "Fast Food"];
var currentIndex: number = 0;
function handleDislike() {
    currentIndex += 1;
    console.log(foodOptions[currentIndex])

}

const FoodSwipe: FC<FoodSwipeProps> = () => (
    <Stack direction="row" justifyContent="center" sx={{ p: "20px", }}>
    <div className={styles.FoodSwipe} data-testid="FoodSwipe">
            <Card sx={{maxWidth: 345}}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        height="140"
                        src="frontend/src/assets/sushi.png"
                        alt="sushi"
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            { foodOptions[currentIndex] }
                        </Typography>
                    </CardContent>
                </CardActionArea>
                <CardActions>
                    <Stack direction="row" justifyContent="space-between" flex="content">
                    <Button size="small" color="primary" onClick={handleDislike}>
                        <NotInterestedIcon/>
                    </Button>
                    <Button size="small" color="primary" href="/swipe-list">
                        <FavoriteIcon/>
                    </Button>
                    </Stack>
                </CardActions>
            </Card>
    </div>
    </Stack>
);

export default FoodSwipe;
