import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import SwipeListToolbar from './SwipeListToolbar';

describe('<SwipeListToolbar />', () => {
  test('it should mount', () => {
    render(<SwipeListToolbar />);
    
    const swipeListToolbar = screen.getByTestId('SwipeListToolbar');

    expect(swipeListToolbar).toBeInTheDocument();
  });
});