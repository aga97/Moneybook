import './App.css';
import Drawer from './components/MainDrawer.js'
import FloatingActionButtons from './components/FloatingMenu.js';
import { Route } from 'react-router-dom';
import SignIn from './components/SignIn.js';
import { Button } from '@material-ui/core';
import { signIn, signUp } from './api/SignApi';
import SignUp from './components/SignUp';

function App() {
  return (
    <div className="App">      
      <Route exact path="/" component={SignIn} />      
      <Button onClick={signIn}>
        fetch
      </Button>
      <Button onClick={signUp}>
        TEST1
      </Button>
      <Route path="/main" component={Drawer} />
      <Route path="/login" component={SignIn} />
      <Route path="/register" component={SignUp} />
      <FloatingActionButtons />
    </div>
  );
}

export default App;
