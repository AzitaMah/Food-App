import React from 'react';
import './App.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import HomeScreen from './HomeScreen';
import {
    BottomNavigation,
    BottomNavigationAction,
    Box,
    CssBaseline,
    Paper,
} from "@mui/material";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import SwipeIcon from '@mui/icons-material/Swipe';

//Fixed Tabbar which is always displayed
function FixedBottomNavigation() {
    const [value, setValue] = React.useState(0);
    const ref = React.useRef<HTMLDivElement>(null);
    React.useEffect(() => {
        (ref.current as HTMLDivElement).ownerDocument.body.scrollTop = 0;
    }, [value,]);

    return (
        <Box sx={{pb: 7}} ref={ref}>
            <CssBaseline/>
            <Paper sx={{position: 'fixed', bottom: 0, left: 0, right: 0}} elevation={3}>
                <div>
                </HomeScreen>
                </div>
                <BottomNavigation
                    showLabels
                    value={value}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                >
                    <BottomNavigationAction label="Swipe" href="/foodswipe"
                                            icon={<SwipeIcon/>}></BottomNavigationAction>
                    <BottomNavigationAction label="Matches" href="/matches"
                                            icon={<FavoriteBorderIcon/>}></BottomNavigationAction>
                </BottomNavigation>
            </Paper>
        </Box>
    );
}

//different toolbars for different screens
const Toolbar = () => <p>Toolbar</p>
const FoodSwipeToolbar = () => <p>FoodSwipeToolbar</p>

//placeholders for main components
const HomePage = () => <p>Homepage</p>
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
                    <Route path="/" element={<Toolbar/>}/>
                    <Route path="/foodswipe" element={<FoodSwipeToolbar/>}/>
                </Routes>
            </aside>
            <main>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/matches" element={<Matches/>}/>
                    <Route path="/profile" element={<Profile/>}/>

                    <Route path="/foodswipe" element={<FoodSwipe/>}/>
                    <Route path="*" element={<NotFoundPage/>}/>
                </Routes>
                <FixedBottomNavigation></FixedBottomNavigation>
            </main>
        </Router>
    );
}

export default App;
