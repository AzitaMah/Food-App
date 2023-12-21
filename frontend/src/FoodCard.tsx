import React, { useRef, useState } from 'react';



interface FoodCardProps {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  onSwipe: (id: number, direction: 'left' | 'right') => void;
}




//handled when user drags left or right
const FoodCard: React.FC<FoodCardProps> = ({ id, name, description, imageUrl, onSwipe }) => {
  
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
      // calculate the drag distance and determine the swipe direction
      const dragDistance = event.clientX - cardRef.current.getBoundingClientRect().left;
      const swipeDirection = dragDistance > 0 ? 'right' : 'left';

      // customize this threshold based on your desired swipe sensitivity
      const swipeThreshold = 100;

      if (Math.abs(dragDistance) > swipeThreshold) {
        onSwipe(id, swipeDirection);
      }
    }
  };

  return (
    
    <div
      ref={cardRef}
      style={{ width: '300px', height: '400px', border: '1px solid #ccc', margin: '10px' }}
      draggable
      onDragStart={handleDragStart}
      onDragEnd={handleDragEnd}
      onDrag={handleDrag}
    >
      <img src={imageUrl} alt={name} style={{ width: '100%', height: '80%', objectFit: 'cover' }} />
      <div style={{ padding: '10px' }}>
        <h3>{name}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default FoodCard;