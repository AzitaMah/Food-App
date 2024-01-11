import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AdminPage from './AdminPage';

describe('<AdminPage />', () => {
  test('it should mount', () => {
    render(<AdminPage />);
    
    const adminPage = screen.getByTestId('AdminPage');

    expect(adminPage).toBeInTheDocument();
  });
});