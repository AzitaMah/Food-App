import React, { FC } from 'react';
import styles from './LoginToolbar.module.css';

interface LoginToolbarProps {}

const LoginToolbar: FC<LoginToolbarProps> = () => (
  <div className={styles.LoginToolbar} data-testid="LoginToolbar">
    LoginToolbar Component
  </div>
);

export default LoginToolbar;
