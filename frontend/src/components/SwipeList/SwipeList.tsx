import React, { FC } from 'react';
import styles from './SwipeList.module.css';

interface SwipeListProps {}

const SwipeList: FC<SwipeListProps> = () => (
  <div className={styles.SwipeList} data-testid="SwipeList">
    SwipeList Component
  </div>
);

export default SwipeList;
