import React, { FC } from 'react';
import styles from './SwipeListToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

interface SwipeListToolbarProps {}

const SwipeListToolbar: FC<SwipeListToolbarProps> = () => (
  <div className={styles.SwipeListToolbar} data-testid="SwipeListToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" component="div" sx={{ flexGrow: 1, color: 'black' }}>
                  Like Your Favourites
              </Typography>
              <IconButton href="/profile" edge="end" color="inherit" aria-label="menu" sx={{ color: 'black', ml: 2 }}>
                  <AccountCircleIcon />
              </IconButton>
          </Toolbar>
      </AppBar>
  </div>
);

export default SwipeListToolbar;
