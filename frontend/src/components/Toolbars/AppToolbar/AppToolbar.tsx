import React, { FC } from 'react';
import styles from './AppToolbar.module.css';
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

interface AppToolbarProps {}

const AppToolbar: FC<AppToolbarProps> = () => (
    <div className={styles.AppToolbar} data-testid="AppToolbar">
        <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
            <Toolbar variant="dense">
                <IconButton href="/profile" edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                    <AccountCircleIcon />
                </IconButton>
                <Typography variant="h6" color="inherit" component="div">
                    Swipe Your Food
                </Typography>
            </Toolbar>
        </AppBar>
    </div>
);

export default AppToolbar;
