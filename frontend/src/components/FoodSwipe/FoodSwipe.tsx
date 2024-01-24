import React, { FC } from 'react';
import styles from './FoodSwipe.module.css';
import SwipeCard from "../SwipeCard/SwipeCard";

interface FoodSwipeProps {}

const FoodSwipe: FC<FoodSwipeProps> = () => (
  <div className={styles.FoodSwipe} data-testid="FoodSwipe">
    <SwipeCard/>
  </div>
);

export default FoodSwipe;
