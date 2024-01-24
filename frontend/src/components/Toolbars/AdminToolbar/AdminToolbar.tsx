import React, { FC } from 'react';
import styles from './AdminToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

interface AdminToolbarProps {}

const AdminToolbar: FC<AdminToolbarProps> = () => (
  <div className={styles.AdminToolbar} data-testid="AdminToolbar">
      <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
          <Toolbar variant="dense">
              <IconButton href="/profile" edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                  <AccountCircleIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" component="div">
                  Admin Page
              </Typography>
          </Toolbar>
      </AppBar>
  </div>
);

export default AdminToolbar;
