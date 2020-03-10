import React from 'react';
import {BrowserRouter as Router, Switch, Route} from  'react-router-dom';
import Home from './Components/Home.jsx';
import time from './Components/TimeRemaining.jsx';
function App() {
  return (
    <Router>
      <h1 style={{marginLeft:"30px"}}>
    Hackathon 2020 冲鸭 </h1>
        <Switch>
            <Route path="/home" exact component={Home} />
            <Route path="/time" exact component={time} />

    </Switch>


    </Router>
  );
}

export default App;
