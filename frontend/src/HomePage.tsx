import React, { useState } from 'react';
import ProfileCard from './ProfileCard';

const HomeScreen: React.FC = () => {
  const initialProfiles = [
    { id: 1, name: 'Matt Smith', age: 24, imageUrl: 'matt.jpg' },
    { id: 2, name: 'Jane Tristan', age: 22, imageUrl: 'jane.jpg' },

  ];

  const [profiles, setProfiles] = useState(initialProfiles);

  const handleSwipe = (id: number, direction: 'left' | 'right') => {

    console.log(`Swiped ${direction} on profile with ID ${id}`);
  };

  return (
    <div>
      <h1>Dating Food App</h1>
      <div>
        
   
{/*       {profiles.map(profile => (
          <ProfileCard
            key={profile.id}
            id={profile.id}
            name={profile.name}
            age={profile.age}
            imageUrl={profile.imageUrl}
            onSwipe={handleSwipe}
          /> 

          
        ))} */}
      </div>
    </div>
  );
}

export default HomeScreen;