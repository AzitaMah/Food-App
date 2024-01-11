import React, { FC } from 'react';
import styles from './ProfileToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

interface ProfileToolbarProps {}

const ProfileToolbar: FC<ProfileToolbarProps> = () => (
  <div className={styles.ProfileToolbar} data-testid="ProfileToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                  <MenuIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" component="div">
                  Your Profile
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default ProfileToolbar;
