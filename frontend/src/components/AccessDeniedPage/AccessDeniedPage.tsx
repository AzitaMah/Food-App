import React, { FC } from 'react';
import styles from './AccessDeniedPage.module.css';
import {Button, Container, Typography} from "@mui/material";
import {Link} from "react-router-dom";

interface AccessDeniedPageProps {}

const AccessDeniedPage: FC<AccessDeniedPageProps> = () => (
  <div className={styles.AccessDeniedPage} data-testid="AccessDeniedPage">
      <Container>
          <Typography variant="h1">
              Access denied
          </Typography>
          <Typography variant="h5" paragraph>
              You don't have the required permissions and are not authorized to access this page.
          </Typography>
          <Button variant="contained" color="primary" component={Link} to="/login">
              Go to Login
          </Button>
      </Container>
  </div>
);

export default AccessDeniedPage;
