import React, { FC } from 'react';
import styles from './AdminPage.module.css';

interface AdminPageProps {}

const AdminPage: FC<AdminPageProps> = () => (
  <div className={styles.AdminPage} data-testid="AdminPage">
    AdminPage Component
  </div>
);

export default AdminPage;
