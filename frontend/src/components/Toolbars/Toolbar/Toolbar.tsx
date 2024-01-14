import React, { FC } from 'react';
import styles from './Toolbar.module.css';
import {AppBar, Box, IconButton, Typography} from "@mui/material";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

interface ToolbarProps {}

const Toolbar: FC<ToolbarProps> = () => (
  <div className={styles.Toolbar} data-testid="Toolbar">
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
                      Dating-Food-App
                  </Typography>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default Toolbar;
