import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Reset } from "styled-reset"; // 크로스 브라우징을 위해 사용

import Home from "./pages/Home";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import { GlobalStyle } from "./styles/GlobalStyle";

const App = () => {
  return (
    <BrowserRouter>
      <Reset />
      <GlobalStyle>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="/login" element={<Login />}></Route>
          </Routes>
        </div>
      </GlobalStyle>
    </BrowserRouter>
  );
};

export default App;
