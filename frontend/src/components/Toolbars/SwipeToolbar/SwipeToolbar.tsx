import React, { FC } from 'react';
import styles from './SwipeToolbar.module.css';
import {AppBar, Box, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

interface SwipeToolbarProps {}

const SwipeToolbar: FC<SwipeToolbarProps> = () => (
  <div className={styles.SwipeToolbar} data-testid="SwipeToolbar">
      <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
              <Toolbar>
                  <IconButton
                      size="large"
                      edge="start"
                      color="inherit"
                      aria-label="menu"
                      sx={{ mr: 2 }}
                  >
                      <MenuIcon />
                  </IconButton>
                  <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                      Food-Swipe
                  </Typography>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default SwipeToolbar;
