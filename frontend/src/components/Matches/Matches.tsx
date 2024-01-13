import React, { FC } from 'react';
import styles from './Matches.module.css';

interface MatchesProps {}

const Matches: FC<MatchesProps> = () => (
  <div className={styles.Matches} data-testid="Matches">
    Matches Component
  </div>
);

export default Matches;
