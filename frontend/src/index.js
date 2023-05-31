import React, {createContext} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './styles/reset.css'
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";
import Account from "./store/account";
import {AccountProvider} from "./store/AccountProvider";

const root = ReactDOM.createRoot(document.getElementById('root'));

//export const Context = createContext({store})

root.render(
  <React.StrictMode>
      <AccountProvider>
        <BrowserRouter>
            <App />
        </BrowserRouter>
      </AccountProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals


reportWebVitals();
