/*import React from "react";
import Login from "../pages/Login.jsx";
import axios from '../config/axios';

const ProtectedRoute = ({ children }) => {
    const token = localStorage.getItem("token");
    if(token){
        console.log(token);
        console.log(axios.defaults.headers.common['Authorization'])
        if (token != axios.defaults.headers.common['Authorization']) {
            return <Login/>;
        }
    }else{
        console.log("entramos al else")
        if(axios.defaults.headers.common['Authorization'] === null){
            return <Login/>;
        }
    }
    return children;
};

export default ProtectedRoute;*/

import React from "react";
import { Route } from "react-router-dom";
import Login from "../pages/Login.jsx";

const ProtectedRoute = ({ children }) => {
    const token = localStorage.getItem("token");
    if (!token) {
        return <Login/>;
    }

    return children;
};

export default ProtectedRoute;