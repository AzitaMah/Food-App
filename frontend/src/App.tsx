import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes, useLocation} from 'react-router-dom';
import {
    BottomNavigation,
    BottomNavigationAction,
    Box,
    CssBaseline,
    Paper,
} from "@mui/material";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ChecklistRtlIcon from '@mui/icons-material/ChecklistRtl';
import SwipeIcon from '@mui/icons-material/Swipe';
import Registration from "./components/Registration/Registration";
import Login from "./components/Login/Login";
import NotFoundPage from "./components/NotFoundPage/NotFoundPage";
import AccessDeniedPage from "./components/AccessDeniedPage/AccessDeniedPage";
import LoginToolbar from "./components/Toolbars/LoginToolbar/LoginToolbar";
import MatchesToolbar from "./components/Toolbars/MatchesToolbar/MatchesToolbar";
import ProfileToolbar from "./components/Toolbars/ProfileToolbar/ProfileToolbar";
import RegistrationToolbar from "./components/Toolbars/RegistrationToolbar/RegistrationToolbar";
import ProfileScreen from "./components/Profile/Profile";
import Matches from "./components/Matches/Matches";
import SwipeList from "./components/SwipeList/SwipeList";
import FoodSwipe from "./components/FoodSwipe/FoodSwipe";
import AppToolbar from "./components/Toolbars/AppToolbar/AppToolbar";
import SwipeListToolbar from "./components/Toolbars/SwipeListToolbar/SwipeListToolbar";

//Fixed Tabbar which is always displayed
function FixedBottomNavigation() {
    const location = useLocation();
    const [value, setValue] = useState(0);

    // Effekt, um den Zustand basierend auf der aktuellen URL zu aktualisieren
    useEffect(() => {
        const path: string = location.pathname;
        //to highlight correct bottom navigation icon
        if (path === '/') {
            setValue(0);
        } else if(path === '/swipe-list') {
            setValue(1);
        } else if (path === '/matches') {
            setValue(2);
        } else {
            setValue(3);
        }

        //to hide bottom navigation for special urls
        /*if (!(path === '/login' || path === '/registration' ||path === '/access-denied')) {
            hideBottomNavBar = false;
        }*/
    }, [location.pathname]);

    if(hideBottomNavBar) {
        return null;
    }
    return (
        <Box sx={{ pb: 7 }}>
            <CssBaseline />
            <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>
                <BottomNavigation
                    showLabels
                    value={value}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                >
                    <BottomNavigationAction label="Swipe" href="/" icon={<SwipeIcon />} />
                    <BottomNavigationAction label="Candidates" href="/swipe-list" icon={<ChecklistRtlIcon />} />
                    <BottomNavigationAction label="Matches" href="/matches" icon={<FavoriteBorderIcon />} />
                </BottomNavigation>
            </Paper>
        </Box>
    );
}
let hideBottomNavBar: boolean = false;

const App: React.FC = () => {
    return (
        <Router>
            <aside>
                <Routes>
                    <Route path="/" element={<AppToolbar/>}/>
                    <Route path="/login" element={<LoginToolbar/>}/>
                    <Route path="/matches" element={<MatchesToolbar/>}/>
                    <Route path="/profile" element={<ProfileToolbar/>}/>
                    <Route path="/swipe-list" element={<SwipeListToolbar/>}/>
                    <Route path="/registration" element={<RegistrationToolbar/>}/>
                </Routes>
            </aside>
            <main>
                <Routes>
                    <Route path="/" element={<FoodSwipe/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/matches" element={<Matches/>}/>
                    <Route path="/profile" element={<ProfileScreen/>}/>
                    <Route path="/swipe-list" element={<SwipeList/>}/>
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
