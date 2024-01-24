import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import FoodSwipe from './FoodSwipe';

describe('<FoodSwipe />', () => {
  test('it should mount', () => {
    render(<FoodSwipe />);
    
    const foodSwipe = screen.getByTestId('FoodSwipe');

    expect(foodSwipe).toBeInTheDocument();
  });
});