import { useState } from "react";
import { SignupWrap } from "../styles/MainStyle";

const SignUp = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [nickname, setNickname] = useState("");
  const [name, setName] = useState("");
  const [birth, setBirth] = useState("");
  const [address, setAddress] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [userType, setUserType] = useState("");

  return (
    <>
      <SignupWrap>
        <section>
          <h1>회원가입</h1>
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <input type="text" />
          <button>가입하기</button>
        </section>
      </SignupWrap>
    </>
  );
};

export default SignUp;
