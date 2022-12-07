import styled from "styled-components";

export const SignupWrap = styled.div`
  section {
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
    align-items: center;
    gap: 2rem;
    margin: 0 auto;
    padding: 3rem 0;
    max-width: 700px;
    background-color: #ddd;
    input {
      width: 80%;
      height: 50px;
      border: none;
    }
    button {
      width: 80%;
      height: 40px;
      border-radius: 10px;
    }
  }
`;
