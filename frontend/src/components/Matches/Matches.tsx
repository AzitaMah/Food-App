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

const dummyPerson: Person = {
    userName: "maxi_food",
    firstName: "Max",
    contactInformation: "insta: maxixmuster",
    birthDate: "01.08.2007",
    image: "3",
}

const dummyPersons: Person[] = Array.from({length: 15}, () => dummyPerson);

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
