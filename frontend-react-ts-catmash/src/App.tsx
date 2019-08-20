import * as React from 'react';
import { BrowserRouter, Link, Route } from 'react-router-dom';
import './App.css';
import Button from './components/Button';
import CatRankingsUI from './containers/CatRankingsUI';
import CatsProposalUI from './containers/CatsProposalUI';

class App extends React.Component<{}, any> {
  public render() {
    return (
    <BrowserRouter>
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Welcome to CatMash</h1>
          <div style={{display: 'flex'}}>
          <Link to={`/`} className="link">
            <Button text="choose a cat" />
          </Link>
          <Link to={`/ranking`} className="link">
            <Button text="ranking" />
          </Link>
          </div>
        </header>
        <Route exact path="/" component={CatsProposalUI} />
        <Route exact path="/ranking" component={CatRankingsUI} />
      </div>
      </BrowserRouter>
    );
  }
}

export default App;
