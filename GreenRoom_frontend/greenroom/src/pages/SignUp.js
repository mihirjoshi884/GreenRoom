import React, { useState } from "react";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import NavBar from "../components/navBar";
import "../pages/pageCss/SignUpCss.css";

const SignUp = () => {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [cPassword, setcPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [userType, setUserType] = useState("");
  const [confirmationCode, setConfirmationCode] = useState("");
  const [showConfirmationCode, setShowConfirmationCode] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    switch (name) {
      case "fullName":
        setFullName(value);
        break;
      case "email":
        setEmail(value);
        break;
      case "username":
        setUsername(value);
        break;
      case "password":
        setPassword(value);
        break;
      case "cPassword":
        setcPassword(value);
        break;
      case "phone":
        setPhone(value);
        break;
      case "userType":
        setUserType(value);
        break;
      default:
        break;
    }
  };

  const handleConfirmationCodeChange = (e) => {
    setConfirmationCode(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Your form submission logic here
    const formData = {
      fullName,
      email,
      username,
      password,
      cPassword,
      phone,
      userType,
    };

    console.log("Form Data:", formData);

    // Add your form submission code here
    if (password === cPassword) {
      console.log("password is matching");
      try {
        const response = await fetch("http://localhost:9002/idp-auth/public/signup", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
          mode: 'cors',
        });

        if (response.status === 200) {
          console.log("status is 200");
          
          setShowConfirmationCode(true);
        } else {
          console.log("status is", response.status);
          console.log("Registration failed");
          setPassword("");
          setcPassword("");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    } else {
      console.log("password is not matching");
      setPassword("");
      setcPassword("");
    }
  };

  const handleConfirmationCodeSubmit = async (e) => {
    e.preventDefault();
    // Your confirmation code submission logic here
    console.log("Confirmation Code:", confirmationCode);
    console.log("username:", username);

    try{
        let confirmCodeResponse = await fetch("http://localhost:9002/idp-auth/public/confirm-signup",{

            method:"POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              confirmationCode: confirmationCode,
              username: username
          }),
            mode: 'cors', 

        });
        if(confirmCodeResponse.status === 200){
          console.log("user is registered succeessfully and verification is done");
          toast.success('Registration successful!');
        }else toast.error("something went wrong pls try again");
    }catch(error){
        console.log(error);
    }
  };

  return (
    <>
      <div className="navBarContainer">
        <NavBar />
      </div>
      <div className="SignUpContainer">
        <div className="SignUpBox">
          {showConfirmationCode ? (
            <form onSubmit={handleConfirmationCodeSubmit} className="SignUpBoxForm">
              <h3>Confirmation Code</h3>
              <input
                type="text"
                placeholder="Enter Confirmation Code"
                name="confirmationCode"
                value={confirmationCode}
                onChange={handleConfirmationCodeChange}
                required
              />
              <button
                type="submit"
                style={{
                  width: '220px',
                  height: '35px',
                  margin: '15px 10px',
                  background: 'linear-gradient(to right, #556B2F, #8FBC8F)',
                  borderRadius: '30px',
                  border: '0',
                  outline: 'none',
                  color: '#fff',
                  cursor: 'pointer',
                }}
              >
                Submit Confirmation Code
              </button>
            </form>
          ) : (
            <form onSubmit={handleSubmit} className="SignUpBoxForm">
              <h3>CREATE ACCOUNT</h3>
              <input type="text" placeholder="Full Name" name="fullName" value={fullName} onChange={handleInputChange} required />
              <input type="text" placeholder="Email Address" name="email" value={email} onChange={handleInputChange} required />
              <input type="text" placeholder="Username" name="username" value={username} onChange={handleInputChange} required />
              <input type="password" placeholder="Password" name="password" value={password} onChange={handleInputChange} required />
              <input type="password" placeholder="Confirm Password" name="cPassword" value={cPassword} onChange={handleInputChange} required />
              <input type="number" placeholder="Phone Number" name="phone" value={phone} onChange={handleInputChange} />
              <div className="ButtonBox">
                <div className="dev-client-box">
                  <label htmlFor="developer">Developer</label>
                  <input
                    type="radio"
                    name="userType"
                    id="dev"
                    value="developer"
                    checked={userType === "developer"}
                    onChange={handleInputChange}
                  />
                  <label htmlFor="client">Client</label>
                  <input
                    type="radio"
                    name="userType"
                    id="client"
                    value="client"
                    checked={userType === "client"}
                    onChange={handleInputChange}
                  />
                </div>
                <button
                  type="submit"
                  style={{
                    width: '110px',
                    height: '35px',
                    margin: '15px 10px',
                    background: 'linear-gradient(to right, #556B2F, #8FBC8F)',
                    borderRadius: '30px',
                    border: '0',
                    outline: 'none',
                    color: '#fff',
                    cursor: 'pointer',
                  }}
                >
                  Submit
                </button>
              </div>
            </form>
          )}
        </div>
      </div>
      <ToastContainer />
    </>
  );
}

export default SignUp;
