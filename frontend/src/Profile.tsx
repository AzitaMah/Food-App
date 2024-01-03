import React from "react";
import HomeScreen from "./HomeScreen";
import { Box, Stack } from "@mui/material";
import foodSwipe from "./components/Homepage/foodSwipe.jpg"

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
  {firstName: "Peter", 
   lastName: "Pan", 
   contact: "Hürstraße 47, Nimmerland", 
   birthDate: "01.08.2007", 
   food: initialFoods[0], 
   hobbies: ["Fußball", "Tiere Töten"], 
   description: "Ich bin Peter Pan", image: "17cm"
  }


const ProfileScreen: React.FC = () => {

    const profileData = [
        
    ]
    
    return (
      <Box>
        <Stack> <img src={foodSwipe}/> </Stack>
      <div>
        <h1></h1>
        <div>   
            {
              mockPerson.firstName + " " +
              mockPerson.lastName
            }  
        </div>
        <div>
            <b>
                ProfileName, LastName,  
            </b>
        </div>
      </div>
      <Box></Box>
      </Box>
    );
  }
  
  export default ProfileScreen ;