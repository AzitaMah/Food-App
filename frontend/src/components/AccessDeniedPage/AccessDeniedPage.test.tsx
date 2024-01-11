import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AccessDeniedPage from './AccessDeniedPage';

describe('<AccessDeniedPage />', () => {
  test('it should mount', () => {
    render(<AccessDeniedPage />);
    
    const accessDeniedPage = screen.getByTestId('AccessDeniedPage');

    expect(accessDeniedPage).toBeInTheDocument();
  });
});