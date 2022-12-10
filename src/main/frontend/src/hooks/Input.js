const Input = ({ text }) => {
  console.log(text);
  return (
    <>
      <div>
        <p>{text}</p>
        <input type="text" />
      </div>
      <span></span>
    </>
  );
};

export default Input;
