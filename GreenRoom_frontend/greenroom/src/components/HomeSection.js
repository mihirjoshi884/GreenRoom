import React, { useRef, useLayoutEffect, useState } from "react";
import homeSectionCSS from "./component-css/homeSection.css";
import TypeWriterEffect from 'react-typewriter-effect';
import { useNavigate } from "react-router-dom";
 


function HomeSection() {
  const [typewriterWidth, setTypewriterWidth] = useState(0);

  const handleTypingDone = () => {
    // Trigger re-render to restart the typing animation
    setTypewriterWidth(0); // Reset the width to trigger useLayoutEffect
  };

  const typewriterRef = useRef();
  
  useLayoutEffect(() => {
    // Measure the width of the typewriter text after typing is done
    const typewriterText = document.querySelector(".typewriter-text");
    if (typewriterText) {
      const width = typewriterText.clientWidth;
      setTypewriterWidth(width);
    }
  }, [typewriterWidth]); // Trigger the effect when typewriterWidth changes
  
  const navigate = useNavigate();
  const goToLoginSignUp = () => {
    // eslint-disable-next-line no-restricted-globals
    navigate("/greenroom/loginSignUp")
    // window.location.href = "/greenroom/loginSignUp";
  };
  return (
    <section className="home-section" style={homeSectionCSS}>
        <div className="getting-started" >
            <div className="type-writer-effect">
            <TypeWriterEffect
                textStyle={{
                fontFamily: 'Red Hat Display',
                color: '#556B2F',
                fontWeight: 600,
                fontSize: '50px',
                position: 'relative',
                top: '70px',
                left: '250px',
                }}
                startDelay={2000}
                cursorColor="#556B2F"
                multiText={[
                'welcome to',
                'welcome creators',
                'welcome freelancers',
                "let's get started with"
                ]}
                multiTextDelay={1500}
                typeSpeed={30}
                onInit={(typewriter) => {
                typewriterRef.current = typewriter;
                typewriter.start();
                }}
                onTypingDone={handleTypingDone}
            />
            </div>

            <p style={{ marginLeft: typewriterWidth + 20 }}>GreenRoom</p>
            <p className="moto-text" style={{'position': 'relative',
                                            'font-size': '20px',
                                            'top': '60px',
                                            'left':'10px',
                                            'color':'#556B2F',
                                            'textAlign':'center',
                                            'text-weight':'500' }}>
                Empowering Creativity, Connecting Minds: Green Room, where project creators and freelancers collaborate to bring visions to fruition.</p>
            <button className="login-signup-button" onClick={goToLoginSignUp}>log In/ Sign Up</button>
            
        </div>

      </section>
  );
}

export default HomeSection;
