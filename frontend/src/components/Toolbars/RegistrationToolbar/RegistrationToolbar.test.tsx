import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import RegistrationToolbar from './RegistrationToolbar';

describe('<RegistrationToolbar />', () => {
  test('it should mount', () => {
    render(<RegistrationToolbar />);
    
    const registrationToolbar = screen.getByTestId('RegistrationToolbar');

    expect(registrationToolbar).toBeInTheDocument();
  });
});