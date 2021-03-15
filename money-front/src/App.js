import './App.css';
import Drawer from './components/MainDrawer.js'
import { Route } from 'react-router-dom';
import SignIn from './components/SignIn.js';

import SignUp from './components/SignUp';

function App() {

  return (
    <div className="App">      
      <Route exact path="/" component={SignIn} />       
      <Route path="/main" component={Drawer} />
      <Route path="/login" component={SignIn} />
      <Route path="/register" component={SignUp} />
    </div>
  );
}

export default App;
