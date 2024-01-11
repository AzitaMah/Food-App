import React, { FC } from 'react';
import styles from './ProfileToolbar.module.css';
import {AppBar, Toolbar, Typography} from "@mui/material";

interface ProfileToolbarProps {}

const ProfileToolbar: FC<ProfileToolbarProps> = () => (
  <div className={styles.ProfileToolbar} data-testid="ProfileToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" component="div">
                  Your Profile
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default ProfileToolbar;
