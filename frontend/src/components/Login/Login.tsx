import {Button, Container, Grid, TextField} from "@mui/material";
import {useState} from "react";

interface AuthResponseDTO {
    accessToken: string;
    tokenType: string;
}

const Login: React.FC = () => {
    

    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    // Alice', 'Smith'

    /**
     * checks if user is already registered.
     * @param event
     */
    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        console.log(formData);
        // TODO: Handle login logic, check for role as well?

        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
              },
            body: JSON.stringify(formData)
        });
        const responseJSON: AuthResponseDTO = await response.json();
        const newAccessToken: string = responseJSON.accessToken;

        localStorage.setItem("accessToken", newAccessToken);
        const loggedInUsername = formData.username;
        localStorage.setItem("username", loggedInUsername)



        console.log(responseJSON);


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
