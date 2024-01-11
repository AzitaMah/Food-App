import React, { FC } from 'react';
import styles from './Toolbar.module.css';

interface ToolbarProps {}

const Toolbar: FC<ToolbarProps> = () => (
  <div className={styles.Toolbar} data-testid="Toolbar">
    Toolbar Component
  </div>
);

export default Toolbar;
