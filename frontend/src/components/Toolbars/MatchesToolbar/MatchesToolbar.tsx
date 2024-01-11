import React, { FC } from 'react';
import styles from './MatchesToolbar.module.css';

interface MatchesToolbarProps {}

const MatchesToolbar: FC<MatchesToolbarProps> = () => (
  <div className={styles.MatchesToolbar} data-testid="MatchesToolbar">
    MatchesToolbar Component
  </div>
);

export default MatchesToolbar;
