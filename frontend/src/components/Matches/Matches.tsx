import React, {FC, useState} from 'react';
import styles from './Matches.module.css';
import {Avatar, Divider, FormControlLabel, List, ListItem, ListItemAvatar, ListItemText, Switch} from "@mui/material";
import ImageIcon from "@mui/icons-material/Image";

interface MatchesProps {
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

const dummyPersons: Person[] =  [dummyPerson1, dummyPerson2, dummyPerson3, dummyPerson4];

const Matches: FC<MatchesProps> = () => {
    const [onlyMatches, setOnlyMatches] = useState(false);

    const handleChange = () => {
        setOnlyMatches((prev) => !prev);
    };

    const listItems = dummyPersons.map(person => (
        <div key={person.userName}>
            <ListItem>
                <ListItemAvatar>
                    <Avatar>
                        <ImageIcon/>
                    </Avatar>
                </ListItemAvatar>
                <ListItemText primary={onlyMatches ? person.firstName : person.userName}
                              secondary={onlyMatches ? person.contactInformation : person.birthDate}/>
            </ListItem>
            <Divider/>
        </div>
    ));

    return (
        <div className={styles.Matches} data-testid="Matches">
            <FormControlLabel
                control={<Switch checked={onlyMatches} onChange={handleChange}/>}
                label="Only show matches"
            />
            <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                {listItems}
            </List>
        </div>
    );
};

export default Matches;
