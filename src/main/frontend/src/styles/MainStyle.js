import styled from "styled-components";

export const LoginWrap = styled.section`
  display: flex;
  align-items: center;

  height: 100vh;

  background: #e1e1e1;

  .login-wrap {
    display: flex;
    flex-flow: column nowrap;
    align-items: center;

    margin: 0 auto;
    padding: 2rem 2rem;

    min-width: 100px;
    width: 400px;

    background-color: #fff;
    box-shadow: 0px 0px 20px 1px #0000004d;
  }
  .title {
    display: flex;
    flex-flow: row wrap;
    gap: 0.5rem;

    padding: 1rem 0 1rem;

    width: 100%;

    text-align: left;
    font-size: 50px;
    font-weight: 600;
  }
  .input-wrap {
    display: flex;
    flex-flow: column nowrap;
    gap: 1rem;

    padding: 1rem 0;

    width: 100%;
    div {
      padding: 0 1rem;
      input {
        flex-grow: 1;

        padding-left: 10px;

        width: 100%;
        height: 2rem;

        border: none;
        border-bottom: 1px solid #cecece;
      }
    }
  }
  .button-wrap {
    position: relative;

    width: 100%;
    button {
      width: 100%;
      height: 40px;

      border-radius: 5px;
      background-color: #7ab4ff;
      color: #fff;
    }
    p {
      position: absolute;
      right: 20px;
      top: calc(100% + 10px);

      color: #ff7272;
      font-size: 13px;
    }
  }
  .signup-wrap {
    display: flex;
    justify-content: center;
    gap: 1rem;

    padding: 1rem 0 0 !important;
    button {
      height: 30px;
      width: 110px;
      background: none;
    }
  }
`;
