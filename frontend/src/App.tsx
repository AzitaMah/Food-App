import React, {useState} from 'react';
import './App.css';
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Typography from "@mui/material/Typography";
import {AccountCircle} from "@mui/icons-material";
import Box from "@mui/material/Box";
import {Drawer, List, ListItem, ListItemText} from "@mui/material";
import ButtonBaseDemo from "./components/Homepage/Homepage";

function App() {

    const [isDrawerOpen, setIsDrawerOpen] = useState(false);

    const toggleDrawer = (open: boolean) => {
        setIsDrawerOpen(open);
    };

  return (
    <div className="App">
        {/* Toolbar */}
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar
                    //style={{ backgroundColor: '#282c34' }}
                >
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={() => toggleDrawer(true)}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Dating-Food App
                    </Typography>
                    <IconButton
                        size="large"
                        aria-label="account of current user"
                        aria-controls="menu-appbar"
                        aria-haspopup="true"
                        color="inherit"
                    >
                        <AccountCircle />
                    </IconButton>
                </Toolbar>
            </AppBar>
        </Box>
        <header className="App-header">

            {/* Food Swipe Button */}
            <div className="display: 'flex', justifyContent: 'center', alignItems: 'center' height: '100vh'">
                <ButtonBaseDemo></ButtonBaseDemo>
            </div>
        </header>
        <Drawer open={isDrawerOpen} onClose={() => toggleDrawer(false)} >
            <List>
                <ListItem>
                    <ListItemText primary="Matches" />
                </ListItem>
                <ListItem>
                    <ListItemText primary="Logout" />
                </ListItem>
            </List>
        </Drawer>
    </div>
  );
}

export default App;
