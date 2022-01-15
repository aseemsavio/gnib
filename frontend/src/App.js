import logo from './logo.svg';
import './App.css';

function App() {


  return (
    <div className="App">
      <header className="App-header">
        <h3>gnib - Graal VM Native Image Builder</h3>
        <button className='button button1'>Upload your JAR file</button>
        <textarea placeholder='Enter your custom "native-image .." command if any' className='textarea'></textarea>
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
