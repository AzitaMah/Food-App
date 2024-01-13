import React from "react";
import './Profile.css';
import Person from "./models/Person";
import { Divider, Paper, Stack } from "@mui/material";
import { mockPerson } from "./Profile";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import foodSwipe from "./components/Homepage/foodSwipe.jpg"
import sizes from "native-base/lib/typescript/theme/base/sizes";



const ProfileCard: React.FC = () => {
    return(
        
        <Paper elevation={3} sx={{ padding: " 0 15px", borderRadius: "20px", display: "flex", alignItems: "center" , }}> 
            <img 
                  src={foodSwipe} 
                  className="profile-imageCard"
            />
            <div className="middle-container">
                <p>{mockPerson.firstName + " " + mockPerson.lastName}</p> 
                <p>{mockPerson.contact}</p>
            </div>
            <div className="right-container">
                <Divider orientation="vertical" flexItem sx={{ borderColor: "rgba(0, 0, 0, 0.24)" }} />
                <FavoriteBorderIcon />
            </div>
            
        </Paper>
    )
}

export default ProfileCard;