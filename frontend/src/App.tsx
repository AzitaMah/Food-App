import React from 'react';
import './App.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import HomeScreen from './HomeScreen';
import FoodCard from './FoodCard';



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
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import AccountCircle from '@mui/icons-material/AccountCircle';
import InputAdornment from '@mui/material/InputAdornment';
import Registration from "./components/Registration/Registration";



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
                    <Button color="inherit">Login</Button>
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

//Loginfield on the loginpage
function Log() {
    return (
        <Grid container justifyContent="center" alignItems="center" height="100vh">
            <Stack
                component="form"
                sx={{
                    width: '25ch',
                }}
                spacing={2}
                noValidate
                autoComplete="off"
            >
                <TextField
                    hiddenLabel
                    id="filled-hidden-label-small"
                    defaultValue="Username"
                    variant="filled"
                    size="small"
                />
                <TextField
                    hiddenLabel
                    id="filled-hidden-label-normal"
                    defaultValue="Password"
                    variant="filled"
                />
            </Stack>
        </Grid>

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
                    <Button color="inherit">Login</Button>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

//Toolbar Register
function RegistrierenToolbar() {
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
                    <Button color="inherit">or already a member?</Button>
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

//different toolbars for different screens
//const Toolbar = () => <p>Toolbar</p>
//const FoodSwipeToolbar = () => <p>FoodSwipeToolbar</p>

//placeholders for main components
//const HomePage = () => <p>Homepage</p>
const LoginPage = () => <p>Login</p>
const Matches = () => <p>Matches</p>
const Profile = () => <p>Profile</p>
const FoodSwipe = () => <p>FoodSwipe</p>

const NotFoundPage = () => <p>404</p>

const App: React.FC = () => {
    return (
        <Router>
            <aside>
                <Routes>
                    <Route path="/" element={<Topbar />} />
                    <Route path="/login" element={
                        <div>
                            <LoginTopbar />
                            <Log />
                        </div>
                    } />
                    <Route path="/matches" element={<MatchesToolbar />} />
                    <Route path="/profile" element={<ProfileToolbar />} />
                    <Route path="/foodswipe" element={<SwipeToolbar />} />
                    <Route path="/registrieren" element={<RegistrierenToolbar />} />
                </Routes>

            </aside>
            <main>
                <Routes>
                    <Route path="/" element={<HomeScreen />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/matches" element={<Matches />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/foodswipe" element={<FoodSwipe />} />
                    <Route path="*" element={<NotFoundPage />} />
                    <Route path="/registrieren" element={<Registration />} />
                </Routes>
                <FixedBottomNavigation></FixedBottomNavigation>
            </main>
        </Router>
    );
}

export default App;
