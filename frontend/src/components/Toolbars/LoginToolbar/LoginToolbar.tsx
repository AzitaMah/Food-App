import React, { FC } from 'react';
import styles from './LoginToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

interface LoginToolbarProps {}

const LoginToolbar: FC<LoginToolbarProps> = () => (
  <div className={styles.LoginToolbar} data-testid="LoginToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                  <MenuIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" component="div">
                  Login Dating-Food-App
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default LoginToolbar;
