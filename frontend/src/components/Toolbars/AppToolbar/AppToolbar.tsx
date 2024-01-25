import React, { FC } from 'react';
import styles from './AppToolbar.module.css';
import { AppBar, IconButton, Toolbar, Typography } from "@mui/material";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

interface AppToolbarProps {}

const AppToolbar: FC<AppToolbarProps> = () => (
    <div className={styles.AppToolbar} data-testid="AppToolbar">
        <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
            <Toolbar variant="dense">
                <Typography variant="h6" color="inherit" component="div" sx={{ flexGrow: 1, color: 'black' }}>
                    Swipe Your Food
                </Typography>
                <IconButton href="/profile" edge="end" color="inherit" aria-label="menu" sx={{ color: 'black', ml: 2 }}>
                    <AccountCircleIcon />
                </IconButton>
            </Toolbar>
        </AppBar>
    </div>
);

export default AppToolbar;
