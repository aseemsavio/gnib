import './App.css';

import React, { useState } from 'react';
import axios from 'axios';

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
        <h3>gnib - Graal VM Native Image Builder</h3>
        <button className='button button1'>Upload JAR file</button>
        <textarea
          placeholder='Enter your custom "native-image .." command if any'
          className='textarea'
          onChange={handleChange}
        />
        <button
          className='button button1'
          onClick={sendArguments}
        >Apply Arguments</button>
        <small>{compilationArgsResp}</small>

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
