import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import SwipeList from './SwipeList';

describe('<SwipeList />', () => {
  test('it should mount', () => {
    render(<SwipeList />);
    
    const swipeList = screen.getByTestId('SwipeList');

    expect(swipeList).toBeInTheDocument();
  });
});