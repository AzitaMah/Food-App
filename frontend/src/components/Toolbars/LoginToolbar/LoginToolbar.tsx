import React, { FC } from 'react';
import styles from './LoginToolbar.module.css';
import {AppBar, Toolbar, Typography} from "@mui/material";

interface LoginToolbarProps {}

const LoginToolbar: FC<LoginToolbarProps> = () => (
  <div className={styles.LoginToolbar} data-testid="LoginToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" component="div" sx={{ flexGrow: 1, color: 'black'}}>
                  Login
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default LoginToolbar;
