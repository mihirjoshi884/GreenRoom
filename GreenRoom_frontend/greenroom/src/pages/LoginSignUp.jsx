import React, { useState } from 'react';
import NavBar from '../components/navBar';
import loginCss from './pageCss/login.css';
import { doLogin, getCurrentUser, getUserGroup } from '../auth/authIndex';
import { toast } from 'react-toastify';

function LoginSignUp() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const handleLogin = async () => {
    // Your login logic here
    console.log('Username:', username);
    console.log('Password:', password);
    console.log('Remember Me:', rememberMe);
  
    try {
      const loginResponse = await fetch('http://localhost:9002/idp-auth/public/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username,
          password,
        }),
      });
    
      if (loginResponse.status === 200) {
        try {
          const data = await loginResponse.json(); // Parse the response as JSON
       
          doLogin(data, () => {
            console.log("login detail is saved to local storage");
            console.log(getCurrentUser());
            toast.success("login success !!");
          });
          // getUserGroup();

        } catch (error) {
          console.error("Error parsing JSON:", error);
        }
      } else {
        // Handle non-200 status code
        console.error("Login failed with status:", loginResponse.status);
      }
    } catch (error) {
      console.error('Error:', error);
      // Handle the error appropriately
    }
  };
  
  return (
    <>
      <header>
        <link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" />
        <link rel="stylesheet" href={loginCss} />
      </header>

      <body>
        <NavBar />
        <div className="wrapper">
          <div className="login_box">
            <div className="login-header">
              <span>login</span>
            </div>
            <div className="input_box">
              <input
                type="text"
                name="username"
                id="username"
                className="input_field"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <label htmlFor="username" className="label">
                username
              </label>
              <i className="bx bx-user icon"></i>
            </div>
            <div className="input_box">
              <input
                type="password"
                name="password"
                id="password"
                className="input_field"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <label htmlFor="password" className="label">
                password
              </label>
              <i className="bx bx-lock-alt icon"></i>
            </div>
            <div className="remember-forgot">
              <div className="remember-me">
                <input
                  type="checkbox"
                  id="remember"
                  checked={rememberMe}
                  onChange={() => setRememberMe(!rememberMe)}
                />
                <label htmlFor="remember">Remember</label>
              </div>
              <div className="forgot">
                <a href="#">forgot password?</a>
              </div>
            </div>
            <div className="input_box">
              <input type="submit" className="input-submit" value="login" onClick={handleLogin} />
            </div>
            <div className="register">
              <span>
                don't have an account? <a href="/greenroom/SignUp">Register</a>
              </span>
            </div>
          </div>
        </div>
      </body>
    </>
  );
}

export default LoginSignUp;
