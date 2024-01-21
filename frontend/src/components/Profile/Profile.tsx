import React, { useEffect } from "react";
import { Box, Divider, Paper, Stack, Typography, useMediaQuery, useTheme } from "@mui/material";
import burger from "../../assets/burger.jpg";
import './Profile.module.css';
import Person from "../../models/Person";
import Food from "../../models/Food";
import RestaurantMenuIcon from '@mui/icons-material/RestaurantMenu';
import ProfileCard from "./ProfileCard";
import { access } from "fs";
import { log } from "console";


interface ExtendedPerson extends Person {
  hobbies : string[],
  description: string,
}

const initialFoods: Food[] = [
  { id: 1, name: 'Pizza', description: 'Delicious pizza with various toppings', imageUrl: 'pizza.jpg' },
  { id: 2, name: 'Burger', description: 'Classic burger with a juicy patty', imageUrl: 'burger.jpg' },
  { id: 3, name: 'Sushi', description: 'Fresh and tasty sushi rolls', imageUrl: 'sushi.jpg' },
  
];

export const mockPerson: ExtendedPerson = 
  {
    image: "17cm",
    firstName: "Peter", 
    lastName: "Pan", 
    birthDate: new Date("1995-11-16"),
    food: initialFoods[0], 
    contact: "+49 156 696969", 
    

    hobbies: ["Fußball", "Tiere Streicheln", "Basketball", "Enten fangen", "Gaming", "Pokemon Karten sammeln", "Lernen", "Schlafen", "Tinker Bell ärgern"], 
    description: "Ich bin Peter Pan amiefhjviejkv id dvc df9j vc voefko vcovm vvfoei vokfe vi mkvcc dsf9vk dfjvh bsdp09 v dfjkl bv09eqr viuldfsaz v9edfj gv98erqz veasdsadasd", 
  
  }


const ProfileScreen: React.FC = () => {
    const theme = useTheme();

    useEffect(() => {

      async function getProfileData() {
        const accessToken: string | null = localStorage.getItem("accessToken");
        const username: string | null = localStorage.getItem("username")
        console.log(accessToken);
        if (accessToken) {
          const response = await fetch("http://localhost:8080/api/person/"+ username, {
            method: "GET",
            headers: {
              "Authorization": "Bearer " + accessToken
            },
          })
          console.log(await response.json());
        }
      }

       getProfileData();

      

    }, []);


    function calculateAge(birthDate: Date): number {
      let timeDiff = Math.abs(Date.now() - birthDate.getTime());
      return Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    }

    
    return (
      <Stack direction="row" justifyContent="center" sx={{ p: "20px", }}>
        <Stack
          direction={{ xs: 'column', md: 'row' }}
          spacing={{ xs: 2, md: 4 }}
          
          divider={<Divider orientation={useMediaQuery(theme.breakpoints.down("md")) ? "horizontal" : "vertical"} flexItem />}
          sx={{ "& > div": { maxWidth: "500px", flex: "1 1 auto" } }}
        >
          
            <Paper elevation={3} sx={{ padding: "15px", borderRadius: "20px" }}>
              <Stack direction="column" gap={"5px"}>
                <img 
                  src={burger} 
                  style={{width: "300px", margin: "5px auto"}}
                />

                <Stack direction="row" justifyContent="space-between">
                  <Typography variant="h5">{mockPerson.firstName} {mockPerson.lastName}</Typography>

                  <Stack direction="row" alignItems="center" gap="5px">
                    <RestaurantMenuIcon />
                    <Typography variant="h5">{mockPerson.food.name}</Typography>
                  </Stack>
                </Stack>
                <Typography variant="body1">
                  {mockPerson.birthDate.getDate() +  "." + (1 + mockPerson.birthDate.getMonth()) + "." + mockPerson.birthDate.getFullYear() + " "
                  + " (age " + calculateAge(mockPerson.birthDate) + ")"}
                </Typography>
                <Typography variant="body1">{mockPerson.contact}</Typography>
              </Stack>
            </Paper>
          


          <Stack direction="column" gap="20px">
            
            <Paper elevation={3} sx={{ padding: "15px", borderRadius: "20px" }}>
              <Typography variant="h5">Hobbies</Typography>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "8px" }}>
                {mockPerson.hobbies.map((hobby) => (
                  <Box 
                    key={mockPerson.hobbies.findIndex((h) => h === hobby)}
                    sx={{ 
                    padding: "0 10px",
                    borderRadius: "100px",
                    border: "3px solid #0400C0"
                  }}>
                    {hobby}
                  </Box>
                
                ))}
              </Box>
            </Paper>

            <Paper elevation={3} sx={{ padding: "15px", borderRadius: "20px" }}>
              <Typography variant="h5">About me</Typography>
              <Typography variant="body1">{mockPerson.description}</Typography>
            </Paper>


          </Stack>

        </Stack>

      </Stack>
    );
  }
  
  export default ProfileScreen ; 