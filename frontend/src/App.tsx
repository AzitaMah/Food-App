import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import HomeScreen from './components/HomeScreen';


import {
    Toolbar,
    BottomNavigation,
    BottomNavigationAction,
    Box,
    CssBaseline,
    Paper,
} from "@mui/material";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import SwipeIcon from '@mui/icons-material/Swipe';
import Registration from "./components/Registration/Registration";
import Login from "./components/Login/Login";
import NotFoundPage from "./components/NotFoundPage/NotFoundPage";
import AccessDeniedPage from "./components/AccessDeniedPage/AccessDeniedPage";
import LoginToolbar from "./components/Toolbars/LoginToolbar/LoginToolbar";
import MatchesToolbar from "./components/Toolbars/MatchesToolbar/MatchesToolbar";
import ProfileToolbar from "./components/Toolbars/ProfileToolbar/ProfileToolbar";
import SwipeToolbar from "./components/Toolbars/SwipeToolbar/SwipeToolbar";
import RegistrationToolbar from "./components/Toolbars/RegistrationToolbar/RegistrationToolbar";


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

//placeholders for main components
const Matches = () => <p>Matches</p>
const Profile = () => <p>Profile</p>
const FoodSwipe = () => <p>FoodSwipe</p>

const App: React.FC = () => {
    return (
        <Router>
            <aside>
                <Routes>
                    <Route path="/" element={<Toolbar/>}/>
                    <Route path="/login" element={<LoginToolbar/>}/>
                    <Route path="/matches" element={<MatchesToolbar/>}/>
                    <Route path="/profile" element={<ProfileToolbar/>}/>
                    <Route path="/foodswipe" element={
                        <SwipeToolbar/>}/> {/*we probably don't need that because swipe component is visible for Route: '/' */}
                    <Route path="/registration" element={<RegistrationToolbar/>}/>
                    <Route path="*" element={<Toolbar/>}/>
                </Routes>

            </aside>
            <main>
                <Routes>
                    <Route path="/" element={<HomeScreen/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/matches" element={<Matches/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path="/foodswipe" element={<FoodSwipe/>}/>
                    <Route path="/registration" element={<Registration/>}/>
                    <Route path="/access-denied" element={<AccessDeniedPage/>}/>
                    <Route path="*" element={<NotFoundPage/>}/>
                </Routes>
                <FixedBottomNavigation></FixedBottomNavigation>
            </main>
        </Router>
    );
}

export default App;
