import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AppToolbar from './AppToolbar';

describe('<AppToolbar />', () => {
  test('it should mount', () => {
    render(<AppToolbar />);
    
    const appToolbar = screen.getByTestId('AppToolbar');

    expect(appToolbar).toBeInTheDocument();
  });
});