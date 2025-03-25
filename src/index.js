import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { EmployeeProvider } from './context/EmployeeContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <EmployeeProvider>
      <App />
    </EmployeeProvider>
  </React.StrictMode>
);

