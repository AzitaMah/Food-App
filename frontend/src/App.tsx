import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import HomeScreen from './HomeScreen';


import {
    AppBar,
    Toolbar,
    BottomNavigation,
    BottomNavigationAction,
    Box,
    Button,
    CssBaseline,
    IconButton,
    Paper,
    Typography,
} from "@mui/material";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import SwipeIcon from '@mui/icons-material/Swipe';
import MenuIcon from '@mui/icons-material/Menu';
import Registration from "./components/Registration/Registration";
import Login from "./components/Login/Login";
import NotFoundPage from "./components/NotFoundPage/NotFoundPage";
import AccessDeniedPage from "./components/AccessDeniedPage/AccessDeniedPage";



//different toolbars for different screens
//Toolbar
function Topbar() {
    return (
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
                        Dating-Food-App
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

//Logintoolbar
function LoginTopbar() {
    return (
        <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
            <Toolbar variant="dense">
                <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" color="inherit" component="div">
                    Login Dating-Food-App
                </Typography>
            </Toolbar>
        </AppBar>
    )
}

//Matchestoolbar
function MatchesToolbar() {
    return (
        <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
            <Toolbar variant="dense">
                <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" color="inherit" component="div">
                    Your Matches
                </Typography>
            </Toolbar>
        </AppBar>
    )
}

//Profiletoolbar
function ProfileToolbar() {
    return (
        <AppBar position="static" sx={{ backgroundColor: 'lightgrey' }}>
            <Toolbar variant="dense">
                <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" color="inherit" component="div">
                    Your Profile
                </Typography>
            </Toolbar>
        </AppBar>
    )
}

//Swipetoolbar
function SwipeToolbar() {
    return (
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
    );
}

//Registrationtoolbar
function RegistrationToolbar() {
    return (
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
    )
}


//Fixed Tabbar which is always displayed
function FixedBottomNavigation() {
    const [value, setValue] = React.useState(0);
    const ref = React.useRef<HTMLDivElement>(null);
    React.useEffect(() => {
        (ref.current as HTMLDivElement).ownerDocument.body.scrollTop = 0;
    }, [value,]);

    return (

        <Box sx={{ pb: 7 }} ref={ref}>
            <div>
            </div>
            <CssBaseline />
            <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>

                <BottomNavigation
                    showLabels
                    value={value}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                >
                    <BottomNavigationAction label="Swipe" href="/foodswipe"
                        icon={<SwipeIcon />}></BottomNavigationAction>
                    <BottomNavigationAction label="Matches" href="/matches"
                        icon={<FavoriteBorderIcon />}></BottomNavigationAction>
                </BottomNavigation>
            </Paper>
        </Box>
    );
}

//placeholders for main components
const Matches = () => <p>Matches</p>
const Profile = () => <p>Profile</p>
const FoodSwipe = () => <p>FoodSwipe</p>

const App: React.FC = () => {
    return (
        <Router>
            <aside>
                <Routes>
                    <Route path="/" element={<Topbar />} />
                    <Route path="/login" element={ <LoginTopbar/>} />
                    <Route path="/matches" element={<MatchesToolbar />} />
                    <Route path="/profile" element={<ProfileToolbar />} />
                    <Route path="/foodswipe" element={<SwipeToolbar />} /> {/*we probably don't need that because swipe component is visible for Route: '/' */}
                    <Route path="/registration" element={<RegistrationToolbar />} />
                    <Route path="*" element={<Topbar />} />
                </Routes>

            </aside>
            <main>
                <Routes>
                    <Route path="/" element={<HomeScreen />} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="/matches" element={<Matches />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/foodswipe" element={<FoodSwipe />} />
                    <Route path="*" element={<NotFoundPage />} />
                    <Route path="/registration" element={<Registration />} />
                    <Route path="/access-denied" element={<AccessDeniedPage />} />
                </Routes>
                <FixedBottomNavigation></FixedBottomNavigation>
            </main>
        </Router>
    );
}

export default App;
