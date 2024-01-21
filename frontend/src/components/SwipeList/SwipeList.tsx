import {FC, useState} from 'react';
import styles from './SwipeList.module.css';
import {Avatar, Divider, List, ListItem, ListItemAvatar, ListItemText} from "@mui/material";
import ImageIcon from "@mui/icons-material/Image";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
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

const dummyPerson: Person = {
    userName: "maxi_food",
    firstName: "Max",
    contactInformation: "insta: maxixmuster",
    birthDate: "01.08.2007",
    image: "3",
}

const dummyPersons: Person[] = Array.from({length: 15}, () => dummyPerson);


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
            <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                {listItems}
            </List>
        </div>
    );
};

export default SwipeList;
