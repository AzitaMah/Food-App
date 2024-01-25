import {FC, useState} from 'react';
import styles from './SwipeList.module.css';
import {Avatar, Button, Divider, List, ListItem, ListItemAvatar, ListItemText} from "@mui/material";
import ImageIcon from "@mui/icons-material/Image";
import FavoriteIcon from '@mui/icons-material/Favorite';

interface SwipeListProps {
}

interface Person {
    userName: string;
    firstName: string;
    contactInformation: string;
    birthDate: string;
    image: string;
}

const dummyPerson1: Person = {
    userName: "john.doe",
    firstName: "John",
    contactInformation: "+1234567890",
    birthDate: "1990-01-01",
    image: "3",
}
const dummyPerson2: Person = {
    userName: "alice.smith",
    firstName: "Alice",
    contactInformation: "+9876543210",
    birthDate: "1985-05-15",
    image: "3",
}
const dummyPerson3: Person = {
    userName: "michael.j",
    firstName: "Michael",
    contactInformation: "+1122334455",
    birthDate: "1982-09-22",
    image: "3",
}
const dummyPerson4: Person = {
    userName: "emily.w",
    firstName: "Emily",
    contactInformation: "+9876543210",
    birthDate: "1995-07-08",
    image: "3",
}
const dummyPerson5: Person = {
    userName: "liamt",
    firstName: "Liam",
    contactInformation: "+1888777666",
    birthDate: "1999-11-08",
    image: "3",
}
const dummyPerson6: Person = {
    userName: "noahc",
    firstName: "Noah",
    contactInformation: "+1777666555",
    birthDate: "1997-05-12",
    image: "3",
}

const dummyPersons: Person[] =  [dummyPerson1, dummyPerson2, dummyPerson3, dummyPerson4, dummyPerson5, dummyPerson6];


/**
 * Shows list containing every person who swiped the same food as current user
 * @constructor
 */
const SwipeList: FC<SwipeListProps> = () => {
    const [likedPersons, setLikedPersons] = useState<string[]>([]);
    const handleLike = (userName: string) => {
        // Toggle like status for the specified user
        setLikedPersons(prevLikedPersons => (
            prevLikedPersons.includes(userName)
                ? prevLikedPersons.filter(person => person !== userName)
                : [...prevLikedPersons, userName]
        ));
    };

    const listItems = dummyPersons.map(person => (
        <div key={person.userName}>
            <ListItem>
                <ListItemAvatar>
                    <Avatar>
                        <ImageIcon/>
                    </Avatar>
                </ListItemAvatar>
                <ListItemText primary={person.userName} secondary={person.birthDate}/>
                <FavoriteIcon
                    style={{
                        color: likedPersons.includes(person.userName) ? 'red' : 'black',
                        fill: likedPersons.includes(person.userName) ? 'red' : 'black',
                        cursor: 'pointer',
                    }}
                    onClick={() => handleLike(person.userName)}
                />
            </ListItem>
            <Divider/>
        </div>
    ));

    return (
        <div className={styles.SwipeList} data-testid="SwipeList">
            <List sx={{width: '100%', maxWidth: 360 }}>
                {listItems}
            </List>
            <Button href="/matches">
                Save
            </Button>
        </div>
    );
};

export default SwipeList;
