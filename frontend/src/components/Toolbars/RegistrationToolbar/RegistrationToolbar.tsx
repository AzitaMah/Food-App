import React, { FC } from 'react';
import styles from './RegistrationToolbar.module.css';
import {AppBar, Box, Button, Toolbar, Typography} from "@mui/material";

interface RegistrationToolbarProps {}

const RegistrationToolbar: FC<RegistrationToolbarProps> = () => (
  <div className={styles.RegistrationToolbar} data-testid="RegistrationToolbar">
      <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
              <Toolbar>
                  <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                      Register
                  </Typography>
                  <Button color="inherit" href="/login">already a member?</Button>
              </Toolbar>
          </AppBar>
      </Box>
  </div>
);

export default RegistrationToolbar;
