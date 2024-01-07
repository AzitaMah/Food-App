import {Button, Container, Typography, Grid, TextField} from "@mui/material";
import {useState} from "react";

const Registration: React.FC = () => {
    // Zustand für die Formulardaten
    const [formData, setFormData] = useState({
        username: '',
        firstName: '',
        lastName: '',
        birthdate: '',
        contactInfo: '',
        password: '',
    });

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        // Hier kannst du die Formulardaten verwenden (formData)
        console.log(formData);
        // Handle registration logic
    };

    // Funktion zum Aktualisieren des Zustands bei Eingabeänderungen
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    return (
        <Container>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            label="Username"
                            variant="outlined"
                            name="username"
                            value={formData.username}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            fullWidth
                            label="First Name"
                            variant="outlined"
                            name="firstName"
                            value={formData.firstName}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            fullWidth
                            label="Last Name"
                            variant="outlined"
                            name="lastName"
                            value={formData.lastName}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        {/* Implement DatePicker component here */}
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            label="Contact Information"
                            variant="outlined"
                            name="contactInfo"
                            value={formData.contactInfo}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            label="Password"
                            type="password"
                            variant="outlined"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button type="submit" variant="contained" color="primary">
                            Register
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default Registration;
