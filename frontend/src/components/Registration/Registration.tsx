import React, { FC } from 'react';
import styles from './Registration.module.css';
import {Box, Button} from "@mui/material";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import AccountCircle from "@mui/icons-material/AccountCircle";

interface RegistrationProps {}

function RegistrierenTextfields() {
    const handleRegister = () => {
        // logic behind this function
        console.log('Registrierung abgeschlossen!');
    };

    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                height: '75vh',
            }}
        >
            <Box
                sx={{
                    width: '75%',
                    marginLeft: 'auto',
                    marginRight: 'auto',
                }}
            >
                <TextField
                    id="input-username"
                    label="Username"
                    variant="standard"
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                        ),
                    }}
                />
                <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        marginTop: 1,
                    }}
                >
                    <TextField
                        id="input-firstname"
                        label="First Name"
                        variant="standard"
                        sx={{ width: '48%' }} // 48% der Breite für den Vornamen
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <AccountCircle />
                                </InputAdornment>
                            ),
                        }}
                    />
                    <TextField
                        id="input-lastname"
                        label="Last Name"
                        variant="standard"
                        sx={{ width: '48%' }} // 48% der Breite für den Nachnamen
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <AccountCircle />
                                </InputAdornment>
                            ),
                        }}
                    />
                </Box>
                <TextField
                    id="input-contact"
                    label="Contact"
                    variant="standard"
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                        ),
                    }}
                />
                <TextField
                    id="input-birthdate"
                    label="Birthdate"
                    variant="standard"
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                        ),
                    }}
                />
                <TextField
                    id="input-password"
                    label="Password"
                    type="password"
                    variant="standard"
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                        ),
                    }}
                    sx={{ marginTop: 1 }}
                />
                <Button variant="contained" onClick={handleRegister} sx={{ marginTop: 2 }}>
                    Register
                </Button>
            </Box>
        </Box>
    );
}

const Registration: FC<RegistrationProps> = () => (
  <div className={styles.Registration} data-testid="Registration">
    <RegistrierenTextfields/>
  </div>
);

export default Registration;
