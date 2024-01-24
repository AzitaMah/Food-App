import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import SwipeCard from './SwipeCard';

describe('<SwipeCard />', () => {
  test('it should mount', () => {
    render(<SwipeCard />);
    
    const swipeCard = screen.getByTestId('SwipeCard');

    expect(swipeCard).toBeInTheDocument();
  });
});