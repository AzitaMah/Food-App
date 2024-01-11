import React, { FC } from 'react';
import styles from './SwipeToolbar.module.css';

interface SwipeToolbarProps {}

const SwipeToolbar: FC<SwipeToolbarProps> = () => (
  <div className={styles.SwipeToolbar} data-testid="SwipeToolbar">
    SwipeToolbar Component
  </div>
);

export default SwipeToolbar;
