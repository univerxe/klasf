import React from "react";
import logo from './logo.svg';
import './styles/App.css';

import Login from "./components/Login"; 
import Register from "./components/Register";
import StudentList from "./components/StudentList";

function App() {
  return (
    <div className="KLASF">
      <header className="Welcome to KLASF">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>

      {/* render components */}
      <main>
        <Login />
        <Register />
        <StudentList />
      </main>

    </div>
  );
}

export default App;
