import React from "react";
import { Box, Divider, Paper} from "@mui/material";
import { mockPerson } from "./Profile";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import burger from "../../assets/burger.jpg"



const ProfileCard: React.FC = () => {
    return(
        
        <Paper elevation={3} sx={{ padding: " 0 15px", borderRadius: "20px", display: "flex", alignItems: "center" , }}> 
            <img   
                  src={burger} 
                  style={{ width: "100px" }}
            />
            <Box sx={{ marginLeft: "10px", "& > p": { margin: "0 0 10px 0" }, "& > p:first-child": { marginTop: "10px" } }}>
                <p>{mockPerson.firstName + " " + mockPerson.lastName}</p> 
                <p>{mockPerson.contact}</p>
            </Box>
            <Box sx={{ alignSelf: "stretch", marginLeft: "auto", display: "flex", alignItems: "center", gap: "15px" }}>
                <Divider orientation="vertical" flexItem sx={{ borderColor: "rgba(0, 0, 0, 0.24)" }} />
                <FavoriteBorderIcon />
            </Box>
            
        </Paper>
    )
}

export default ProfileCard;