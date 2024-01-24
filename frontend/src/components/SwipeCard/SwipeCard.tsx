import React, { FC } from 'react';
import styles from './SwipeCard.module.css';
import {Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Typography} from "@mui/material";
import FavoriteIcon from '@mui/icons-material/Favorite';
import NotInterestedIcon from '@mui/icons-material/NotInterested';

interface SwipeCardProps {}

const SwipeCard: FC<SwipeCardProps> = () => (
  <div className={styles.SwipeCard} data-testid="SwipeCard">
      <Card sx={{ maxWidth: 345 }}>
          <CardActionArea>
              <CardMedia
                  component="img"
                  height="140"
                  src="frontend/src/assets/sushi.png"
                  alt="sushi"
              />
              <CardContent>
                  <Typography gutterBottom variant="h5" component="div">
                      Sushi
                  </Typography>
              </CardContent>
          </CardActionArea>
          <CardActions>
              <Button size="small" color="primary">
                  <NotInterestedIcon/>
              </Button>
              <Button size="small" color="primary" href="/swipe-list">
                  <FavoriteIcon/>
              </Button>
          </CardActions>
      </Card>
  </div>
);

export default SwipeCard;
