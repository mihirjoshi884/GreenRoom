import NavBar from "../components/navBar.js";

import HomeSection from "../components/HomeSection.js";
import React from "react";

const HomePageStyle = {
    background: 'linear-gradient(to bottom, #F5F5DC, #808000)',
    minHeight: '100vh'  // Fix the typo here
};

function Home() {
    return (
        <>
            <div style={HomePageStyle}>
                <div>
                <NavBar />
                </div> 
                
                <div className="home-section">
                    <HomeSection/>
                </div>
             
            </div>
                
        </>
    );
}

export default Home;
