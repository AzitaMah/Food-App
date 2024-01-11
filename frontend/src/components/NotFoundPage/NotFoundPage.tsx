import React, { FC } from 'react';
import styles from './NotFoundPage.module.css';
import {Button, Container, Typography} from "@mui/material";
import {Link} from "react-router-dom";

interface NotFoundPageProps {}

const NotFoundPage: FC<NotFoundPageProps> = () => (
  <div className={styles.NotFoundPage} data-testid="NotFoundPage">
      <Container>
          <Typography variant="h1">
              404 - Not Found
          </Typography>
          <Typography variant="h5" paragraph>
              Oops! We couldn't find the requested page.
          </Typography>
          <Button variant="contained" color="primary" component={Link} to="/">
              Go Home
          </Button>
      </Container>
  </div>
);

export default NotFoundPage;
