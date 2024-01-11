import React, { FC } from 'react';
import styles from './RegistrationToolbar.module.css';

interface RegistrationToolbarProps {}

const RegistrationToolbar: FC<RegistrationToolbarProps> = () => (
  <div className={styles.RegistrationToolbar} data-testid="RegistrationToolbar">
    RegistrationToolbar Component
  </div>
);

export default RegistrationToolbar;
