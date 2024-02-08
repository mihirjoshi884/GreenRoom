// import { Jwt } from "jsonwebtoken";


export const isLoggedIn =(next)=>{

    let data = localStorage.getItem("data");
    if(data !=null) return true;
    else return false;
    next();
}

export const doLogin = (data, next) => {
    localStorage.setItem("userData", JSON.stringify(data));
    next();
    
}

export const doLoggOut=(next)=>{
    localStorage.removeItem("data");
    next();
}

export const getCurrentUser =()=> {
    if(isLoggedIn){
        return JSON.parse(localStorage.getItem("data")).user;
    }else{
        return false;
    }
}
// export const getUserGroup = () => {
//     const storedData = JSON.parse(localStorage.getItem("data"));
  
//     if (storedData && storedData.accessToken) {
//       const decodedAccessToken = Jwt.decode(storedData.accessToken);
  
//       if (decodedAccessToken && decodedAccessToken['cognito:groups']) {
//         const userGroups = decodedAccessToken['cognito:groups'];
//         // Now you can use the userGroups variable as needed.
//         console.log('User Groups:', userGroups);
//         return userGroups;
//       } else {
//         console.error('Failed to decode accessToken or "cognito:groups" not found.');
//         return null;
//       }
//     } else {
//       console.error('Stored data or accessToken not found in localStorage.');
//       return null;
//     }
//   };
  