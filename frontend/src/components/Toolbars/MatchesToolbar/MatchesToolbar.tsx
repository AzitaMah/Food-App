import React, { FC } from 'react';
import styles from './MatchesToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

interface MatchesToolbarProps {}

const MatchesToolbar: FC<MatchesToolbarProps> = () => (
  <div className={styles.MatchesToolbar} data-testid="MatchesToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                  <MenuIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" component="div">
                  Your Matches
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default MatchesToolbar;
