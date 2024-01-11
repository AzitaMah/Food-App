import React, { FC } from 'react';
import styles from './RegistrationToolbar.module.css';
import {AppBar, Box, Button, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

interface RegistrationToolbarProps {}

const RegistrationToolbar: FC<RegistrationToolbarProps> = () => (
  <div className={styles.RegistrationToolbar} data-testid="RegistrationToolbar">
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
                      Registrieren
                  </Typography>
                  <Button color="inherit" href="/login">already a member?</Button>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default RegistrationToolbar;
