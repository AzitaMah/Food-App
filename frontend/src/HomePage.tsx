import React, { useState } from 'react';
import React, { useState } from 'react';
import FoodCard from './ProfileCard';





const FoodScreen: React.FC = () => {
  // Raw code Foods since not connected yet to the database
  
  const initialFoods = [
    { id: 1, name: 'Pizza', description: 'Delicious pizza with various toppings', imageUrl: 'pizza.jpg' },
    { id: 2, name: 'Burger', description: 'Classic burger with a juicy patty', imageUrl: 'burger.jpg' },
    { id: 3, name: 'Sushi', description: 'Fresh and tasty sushi rolls', imageUrl: 'sushi.jpg' },
    
  ];




  const [foods, setFoods] = useState(initialFoods);

  const handleSwipe = (id: number, direction: 'left' | 'right') => {
    // Handle swipe action (e.g., remove food from the list, update preferences, etc.)
    // This is a placeholder, and you would typically update the state or make API calls.
    console.log(`Swiped ${direction} on food with ID ${id}`);
  };



  return (
    <div>
      <h1>Food Swipe App</h1>
      <div>

        {foods.map(food => (
          <FoodCard
            key={food.id}
            id={food.id}
            name={food.name}
            description={food.description}
            imageUrl={food.imageUrl}
            onSwipe={handleSwipe}
          />
        ))}
      </div>
    </div>
  );
}


export default HomeScreen;