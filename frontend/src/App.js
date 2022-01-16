import './App.css';

import React, { useState } from 'react';
import axios from 'axios';
import FileUpload from './components/FileUpload';

function App() {
  const [compilationArgs, setArguments] = useState("")
  const [compilationArgsResp, setArgumentsResp] = useState("")

  const handleChange = (event) => {
    setArguments(event.target.value)
  }

  const sendArguments = () => {
    axios.post("http://localhost:8080/native-image/arguments", compilationArgs)
    .then(resp => setArgumentsResp(resp.data))
  }

  return (
    <div className="App">
      <header className="App-header">
        <small>{compilationArgs}</small>
        <h3>gnib - Graal VM Native Image Builder</h3>
        <FileUpload/>
        <textarea
          placeholder='Enter your custom "native-image .." command if any'
          className='textarea'
          onChange={handleChange}
        />
        <button className='button button1'>Apply Arguments</button>
        <br></br>
        <small>Building Native Image...</small>
        <div className='flexbox-container'>
          <br></br>
          <button className='button button1'>Download the Native Image</button>
          <button className='button button1'>Download Logs Dump</button>
        </div>
      </header>
    </div>
  );
}

export default App;
