import React from 'react';
import NavBarCss from './component-css/NavBarCss.css';
import GreenRoomLogoImg from '../imgs/GreenRoomLogo.jpeg';

function NavBar() {
  return (
    <>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossOrigin="anonymous"
      />

      <header style={NavBarCss}>
        <a href="/" className="logo">
            <img src={GreenRoomLogoImg} alt="GreenRoomLogo" className='logo-image' />
            <span className='logo-text'>GreenRoom</span>
            
        </a>
        <nav className='navbar'>
            <div>
            <i className="fas fa-times close-btn"></i>
            </div>
            <a href="/greenroom">Home</a>
            <a href="#">About</a>
            <a href="#">Contact Us</a>
        </nav>
        
        <div className="btn">
          <i className="fas fa-times"></i>
        </div>
      </header>
    </>
  );
}

export default NavBar;
