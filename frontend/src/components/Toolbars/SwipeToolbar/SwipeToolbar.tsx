import React, { FC } from 'react';
import styles from './SwipeToolbar.module.css';
import {AppBar, Box, IconButton, Toolbar, Typography} from "@mui/material";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

interface SwipeToolbarProps {}

const SwipeToolbar: FC<SwipeToolbarProps> = () => (
  <div className={styles.SwipeToolbar} data-testid="SwipeToolbar">
      <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
              <Toolbar>
                  <IconButton
                      href="/profile"
                      size="large"
                      edge="start"
                      color="inherit"
                      aria-label="menu"
                      sx={{ mr: 2 }}
                  >
                      <AccountCircleIcon />
                  </IconButton>
                  <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                      Swipe your food
                  </Typography>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default SwipeToolbar;
