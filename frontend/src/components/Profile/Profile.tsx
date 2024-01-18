import React from "react";
import { Box, Divider, Paper, Stack, Typography, useMediaQuery, useTheme } from "@mui/material";
//import foodSwipe from "./components/Homepage/foodSwipe.jpg"
import './Profile.module.css';

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
        contact: "+49 156 1234567",


        hobbies: ["Fußball", "Basketball", "Gaming", "Pokemon Karten sammeln", "Lernen", "Schlafen"],
        description: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At",

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
                spacing={{ xs: 2, md: 4 }}

                divider={<Divider orientation={useMediaQuery(theme.breakpoints.down("md")) ? "horizontal" : "vertical"} flexItem />}
                sx={{ "& > div": { maxWidth: "500px", flex: "1 1 auto" } }}
            >

                <Paper elevation={3} sx={{ padding: "15px", borderRadius: "20px" }}>
                    <Stack direction="column" gap={"5px"}>
                        {/*<img
                            src={foodSwipe}
                            className="profile-image"

                        />*/}

                        <Stack direction="row" justifyContent="space-between">
                            <Typography variant="h5">{mockPerson.firstName} {mockPerson.lastName}</Typography>
                            <Typography variant="h5">{mockPerson.food.name}</Typography>
                        </Stack>
                        <Typography variant="body1">{mockPerson.birthDate}</Typography>
                        <Typography variant="body1">{mockPerson.contact}</Typography>
                    </Stack>
                </Paper>



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
