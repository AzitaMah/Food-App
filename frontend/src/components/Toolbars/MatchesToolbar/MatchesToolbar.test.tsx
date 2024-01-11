import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import MatchesToolbar from './MatchesToolbar';

describe('<MatchesToolbar />', () => {
  test('it should mount', () => {
    render(<MatchesToolbar />);
    
    const matchesToolbar = screen.getByTestId('MatchesToolbar');

    expect(matchesToolbar).toBeInTheDocument();
  });
});