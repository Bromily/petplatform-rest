import axios from "axios";

import { useCallback, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import Input from "../hooks/Input";
import { LoginWrap } from "../styles/MainStyle";

const Login = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [warnning, setWarnning] = useState("");

  const navigate = useNavigate();

  const goToSignUp = () => {
    navigate("/signup");
  };

  const LoginUser = useCallback(() => {
    if (!userId) {
      setWarnning("아이디를 입력해 주세요");

      return;
    }
    if (!password) {
      setWarnning("비밀번호를 입력해 주세요");
      return;
    }

    axios
      .post("/api/signin", {
        userId: userId,
        password: password,
      })
      .then(function (response) {
        // response
        const status = response.status;
        status === 200 ? navigate("/") : alert("실패임 ㅋ");
      })
      .catch(function (error) {
        console.log(error);
        // 오류발생시 실행
      })
      .then(function () {
        // 항상 실행
      });
  }, [userId, password]);

  return (
    <>
      <LoginWrap>
        <div className="login-wrap">
          <div className="title">
            <p>PetPlatform</p>
            <p>Admin</p>
          </div>
          <div className="input-wrap">
            <div>
              <input
                type="text"
                text={"ID"}
                onChange={(e) => {
                  setUserId(e.target.value);
                }}
                value={userId}
                placeholder="ID"
              />
            </div>
            <div>
              <input
                type="password"
                text={"ID"}
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                value={password}
                placeholder="PASSWORD"
              />
            </div>
            <div className="button-wrap">
              <button onClick={LoginUser}>Login</button>
              <p>* {warnning}</p>
            </div>
            <div className="signup-wrap">
              <button>Forgot Password</button>
              <button onClick={goToSignUp}>Sign Up</button>
            </div>
          </div>
        </div>
      </LoginWrap>
    </>
  );
};

export default Login;
