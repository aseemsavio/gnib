import './App.css';

import React, { useState } from 'react';
import FileUpload from './components/FileUpload';
import Arguments from './components/Arguments';
import Download from './components/Download';

function App() {

  const [showFileUpload, setShowFileUpload] = useState(true)
  const [showArguments, setShowArguments] = useState(false)
  const [showDownload, setShowDownload] = useState(false)

  return (
    <div className="App">
      <header className="App-header">
        <h3>gnib - Graal VM Native Image Builder</h3>
        {showFileUpload ? <FileUpload present={setShowFileUpload} next={setShowArguments} /> : null}
        {showArguments ? <Arguments present={setShowArguments} next={setShowDownload}/> : null}
        {showDownload ? <Download present={setShowDownload} /> : null}
      </header>
    </div>
  );
}

export default App;
