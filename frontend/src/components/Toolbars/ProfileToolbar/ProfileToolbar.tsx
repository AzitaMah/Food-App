import React, { FC } from 'react';
import styles from './ProfileToolbar.module.css';

interface ProfileToolbarProps {}

const ProfileToolbar: FC<ProfileToolbarProps> = () => (
  <div className={styles.ProfileToolbar} data-testid="ProfileToolbar">
    ProfileToolbar Component
  </div>
);

export default ProfileToolbar;
