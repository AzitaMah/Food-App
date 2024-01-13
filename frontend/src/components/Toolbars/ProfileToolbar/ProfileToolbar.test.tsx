import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import ProfileToolbar from './ProfileToolbar';

describe('<ProfileToolbar />', () => {
  test('it should mount', () => {
    render(<ProfileToolbar />);
    
    const profileToolbar = screen.getByTestId('ProfileToolbar');

    expect(profileToolbar).toBeInTheDocument();
  });
});