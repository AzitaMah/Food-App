import React, { FC } from 'react';
import styles from './AdminToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

interface AdminToolbarProps {}

const AdminToolbar: FC<AdminToolbarProps> = () => (
  <div className={styles.AdminToolbar} data-testid="AdminToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" component="div" sx={{ flexGrow: 1, color: 'black' }}>
                  Admin Page
              </Typography>
              <IconButton href="/profile" edge="end" color="inherit" aria-label="menu" sx={{ color: 'black', ml: 2 }}>
                  <AccountCircleIcon />
              </IconButton>
          </Toolbar>
      </AppBar>
  </div>
);

export default AdminToolbar;
