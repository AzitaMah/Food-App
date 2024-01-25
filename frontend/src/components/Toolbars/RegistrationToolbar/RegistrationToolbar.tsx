import React, { FC } from 'react';
import styles from './RegistrationToolbar.module.css';
import {AppBar, Box, Button, Toolbar, Typography} from "@mui/material";

interface RegistrationToolbarProps {}

const RegistrationToolbar: FC<RegistrationToolbarProps> = () => (
  <div className={styles.RegistrationToolbar} data-testid="RegistrationToolbar">
      <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
              <Toolbar>
                  <Typography variant="h6" color="inherit" component="div" sx={{ flexGrow: 1, color: 'black' }}>
                      Register
                  </Typography>
                  <Button color="inherit" href="/login" sx={{ color: 'black', ml: 2 }}>already a member?</Button>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default RegistrationToolbar;
