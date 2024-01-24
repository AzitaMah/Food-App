import React, { useState } from 'react';
import FoodCard from './FoodCard';
import Matches from './Matches/Matches';

const FoodScreen: React.FC = () => {
  const initialFoods = [
    { id: 1, name: 'pizza', description: 'Delicious pizza with various toppings', imageUrl: '' },
    { id: 2, name: 'burger', description: 'Classic burger with a juicy patty', imageUrl: '' },
    { id: 3, name: 'sushi', description: 'Fresh and tasty sushi rolls', imageUrl: '' },
    { id: 4, name: 'spaghetti', description: '', imageUrl: '' },
    { id: 5, name: 'satay-chicken', description: '', imageUrl: '' },
    { id: 6, name: 'meatballs', description: '', imageUrl: '' },
    // Fooods
  ];

  const [foods, setFoods] = useState(initialFoods);
  const [matchedFoods, setMatchedFoods] = useState<string[]>([]);
  const [swipeThreshold, setSwipeThreshold] = useState<number>(100);

  const handleSwipe = (id: number, direction: 'left' | 'right') => {
    if (direction === 'right' && id === 3) {
      setMatchedFoods(prev => [...prev, 'Sushi']);
    }

    setFoods(prevFoods => prevFoods.filter(food => food.id !== id));
  };

  return (
    <div>
      <div>
        <h1>Food Swipe App</h1>
        <label>
          Swipe Threshold:
          <input
            type="number"
            value={swipeThreshold}
            onChange={(e) => setSwipeThreshold(Number(e.target.value))}
          />
        </label>
        <div>
          {foods.map(food => (
            <FoodCard
              key={food.id}
              id={food.id}
              name={food.name}
              description={food.description}
              imageUrl={food.imageUrl}
              onSwipe={(id, direction) => handleSwipe(id, direction)}
              swipeThreshold={swipeThreshold}
            />
          ))}
        </div>
      </div>
      {/* {matchedFoods.length > 0 && <Matches matchedFoods={matchedFoods} />} */}
    </div>
  );
}


export default FoodScreen;