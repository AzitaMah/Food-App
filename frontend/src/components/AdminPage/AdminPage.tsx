import React, { FC, useState } from 'react';
import styles from './AdminPage.module.css';
import {
    Button,
    Dialog, DialogActions, DialogContent, DialogContentText,
    DialogTitle,
    Divider,
    List,
    ListItem,
    ListItemText
} from "@mui/material";
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';

interface AdminPageProps {}

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

const AdminPage: FC<AdminPageProps> = () => {
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleDeleteClick = () => {
        //TODO: delete logic
        handleClickOpen();
    };

    const listItems = dummyPersons.map(person => (
        <div key={person.userName}>
            <ListItem>
                <ListItemText primary={person.userName} />
                <DeleteOutlineIcon
                    style={{ color: 'red', cursor: 'pointer' }}
                    onClick={handleDeleteClick}
                />
            </ListItem>
            <Divider/>
        </div>
    ));

    return (
        <div className={styles.AdminPage} data-testid="AdminPage">
            <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                {listItems}
            </List>
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Delete user"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        Are you sure you want to delete the user? This action can't be undone.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleClose}>
                        Delete
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default AdminPage;