import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import SwipeToolbar from './SwipeToolbar';

describe('<SwipeToolbar />', () => {
  test('it should mount', () => {
    render(<SwipeToolbar />);
    
    const swipeToolbar = screen.getByTestId('SwipeToolbar');

    expect(swipeToolbar).toBeInTheDocument();
  });
});