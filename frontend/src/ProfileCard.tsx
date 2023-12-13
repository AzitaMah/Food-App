import React, { useRef, useState } from 'react';

interface ProfileCardProps {
  id: number;
  name: string;
  age: number;
  imageUrl: string;
  onSwipe: (id: number, direction: 'left' | 'right') => void;
}



const ProfileCard: React.FC<ProfileCardProps> = ({ id, name, age, imageUrl, onSwipe }) => {
  const [dragging, setDragging] = useState(false);
  const cardRef = useRef<HTMLDivElement>(null);

  const handleDragStart = () => {
    setDragging(true);
  };



  
  const handleDragEnd = () => {
    setDragging(false);
  };

  const handleDrag = (event: React.DragEvent<HTMLDivElement>) => {
    if (cardRef.current && dragging) {
      
      const dragDistance = event.clientX - cardRef.current.getBoundingClientRect().left;
      const swipeDirection = dragDistance > 0 ? 'right' : 'left';

      
      const swipeThreshold = 100;

      if (Math.abs(dragDistance) > swipeThreshold) {
        onSwipe(id, swipeDirection);
      }
    }
  };

  return (
    
    <div
      ref={cardRef}
      style={{ width: '300px', height: '400px', border: '1px solid #ccc', margin: '15px' }}
      draggable
      onDragStart={handleDragStart}
      
      onDragEnd={handleDragEnd}
      onDrag={handleDrag}
      >
      <p>{age} years old</p>
      
      </div>
  );
}

export default ProfileCard;
