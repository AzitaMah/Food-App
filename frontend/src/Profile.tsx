import React from "react";
import HomeScreen from "./HomeScreen";
import { Box, Divider, Paper, Stack, Typography, useMediaQuery, useTheme } from "@mui/material";
import foodSwipe from "./components/Homepage/foodSwipe.jpg"
import './Profile.css';

// name 
// last name
// (food) 
// ProfileImage
// (Hobbies) 
// (Description) 
// (Bool Edit if true == allow edit) 
// (hidden Id? - if userID == hidden ID) 
// display timer 
// contact information

const initialFoods:  Food[] = [
  { id: 1, name: 'Pizza', description: 'Delicious pizza with various toppings', imageUrl: 'pizza.jpg' },
  { id: 2, name: 'Burger', description: 'Classic burger with a juicy patty', imageUrl: 'burger.jpg' },
  { id: 3, name: 'Sushi', description: 'Fresh and tasty sushi rolls', imageUrl: 'sushi.jpg' },
  
];
// role , userName, password
interface Food{
  id: number,
  name : string,
  description : string,
  imageUrl: string,
}

interface Person {
  firstName : string,
  lastName : string,
  contact : string,
  birthDate : string,
  food : Food,
  hobbies : string[],
  description: string,
  image: string,
}

const mockPerson: Person = 
  {
    image: "17cm",
    firstName: "Peter", 
    lastName: "Pan", 
    birthDate: "01.08.2007", 
    food: initialFoods[0], 
    contact: "+49 156 696969", 
    

    hobbies: ["Fußball", "Tiere Töten", "Basketball", "Enten fangen", "Gaming", "Pokemon Karten sammeln", "Lernen", "Schlafen", "Bitches respecten"], 
    description: "Ich bin Peter Pan du hoe amiefhjviejkv id dvc df9j vc voefko vcovm vvfoei vokfe vi mkvcc dsf9vk dfjvh bsdp09 v dfjkl bv09eqr viuldfsaz v9edfj gv98erqz ve", 
  
  }


const ProfileScreen: React.FC = () => {
    const theme = useTheme();
    const profileData = [
        
    ]
    // direction: { xs: "column", sm: "row" }
    return (
      <Stack direction="row" justifyContent="center" sx={{ p: "20px" }}>
        {/* <Box sx={{ 
          display: "flex", 
          flexDirection: { xs: "column", md: "row" }, 
          border: "1px solid black", 
          maxWidth: "899px", 
          
        }}> */}
        {/* sx={{ border: "1px solid black" }} */}
        <Stack
          direction={{ xs: 'column', md: 'row' }}
          spacing={{ xs: 1, md: 2 }}
          
          divider={<Divider orientation={useMediaQuery(theme.breakpoints.down("md")) ? "horizontal" : "vertical"} flexItem />}
        >
          <Stack direction="column" sx={{ width: "100%" }}>

            <img 
              src={foodSwipe} 
              className="profile-image"
            
            />

            <Stack direction="row" justifyContent="space-between">
              <Typography variant="h5">{mockPerson.firstName} {mockPerson.lastName}</Typography>
              <Typography variant="h5">{mockPerson.food.name}</Typography>
            </Stack>
            <Typography variant="body1">{mockPerson.birthDate}</Typography>
            <Typography variant="body1">{mockPerson.contact}</Typography>

          </Stack>

          <Stack direction="column" gap="20px">
            
            <Paper elevation={3} sx={{ padding: "15px", borderRadius: "20px" }}>
              <Typography variant="h5">Hobbies</Typography>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "8px" }}>
                {mockPerson.hobbies.map((hobby) => (
                  <Box sx={{ 
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