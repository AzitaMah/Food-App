import {Button, Container, Grid, TextField} from "@mui/material";
import {useState} from "react";
import {DatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";

const Login: React.FC = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    /**
     * sends data to database
     * @param event
     */
    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        console.log(formData);
        // TODO: Handle registration logic
    };

    /**
     * handles change on data
     * @param event
     */
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    return (
        <Container style={{ paddingTop: '20px' }}>
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
                            Login
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default Login;
