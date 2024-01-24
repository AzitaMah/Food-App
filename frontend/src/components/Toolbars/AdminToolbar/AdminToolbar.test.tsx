import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AdminToolbar from './AdminToolbar';

describe('<AdminToolbar />', () => {
  test('it should mount', () => {
    render(<AdminToolbar />);
    
    const adminToolbar = screen.getByTestId('AdminToolbar');

    expect(adminToolbar).toBeInTheDocument();
  });
});