
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Home from './pages/Home';
import LoginSignUp from './pages/LoginSignUp';
import SignUp from './pages/SignUp';


function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>

        <Route path='/greenroom' element={<Home/>}/>
        <Route path="/greenroom/loginSignUp" element={<LoginSignUp />} />
        <Route path="/greenroom/SignUp" element={<SignUp />} />
        
      </Routes>
      
      
    </BrowserRouter>
    </>
  );
}

export default App;
