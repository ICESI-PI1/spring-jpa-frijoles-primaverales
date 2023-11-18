import React from "react";
import { useState } from "react";
//import axios from "../config/axios";
import { useNavigate } from "react-router-dom";
import { Navigate } from "react-router-dom";
import { Box, Button, TextField } from "@mui/material";
import axios from '../config/axios';
import setAuthorizationToken from '../config/axios';

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  localStorage.clear();

  const handleClick = () => {
    let user = { username, password };
    axios
    .post("/login", user)
    .then((res) => {
      console.log("Respuesta del servidor:", res);
      const token = res.headers.authorization;
      localStorage.setItem("token", token);
      axios.defaults.headers.common['Authorization'] = token;
      navigate("/books");
    })
    .catch((err) => {
      console.log("Error al intentar autenticar:", err);
    });
    };
  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 1, width: "25ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
        label="username"
        variant="standard"
        value={username}
        onChange={(e) => {
          setUsername(e.target.value);
        }}
      />
      <TextField
        label="password"
        variant="standard"
        value={password}
        onChange={(e) => {
          setPassword(e.target.value);
        }}
      />
      <Button variant="contained" onClick={handleClick}>
        Login
      </Button>
    </Box>
  );
}

export default Login;
