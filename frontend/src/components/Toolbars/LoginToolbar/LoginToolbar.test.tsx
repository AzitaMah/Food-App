import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import LoginToolbar from './LoginToolbar';

describe('<LoginToolbar />', () => {
  test('it should mount', () => {
    render(<LoginToolbar />);
    
    const loginToolbar = screen.getByTestId('LoginToolbar');

    expect(loginToolbar).toBeInTheDocument();
  });
});