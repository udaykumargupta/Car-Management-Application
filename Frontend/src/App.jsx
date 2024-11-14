<<<<<<< HEAD
import {Route, Routes } from "react-router-dom";
import Auth from "./page/Auth/Auth";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUser } from "./State/Auth/Action";
import Home from "./page/home/Home";


function App() {

  const {auth}=useSelector(store=>store);
  const dispatch=useDispatch()

  console.log("auth-----",auth)
  useEffect(()=>{
    dispatch(getUser( auth.jwt || localStorage.getItem("jwt")))
  },[auth.jwt])
  return (
    <>
      {auth.user? (
        <div>
          
          <Routes>
          <Route path="/" element={<Home/>} />
          </Routes>
        </div>
      ):<Auth/>}
    </>
  );
}

export default App;
=======
import './App.css'
function App() {

  return (
      <>

      </>
  )
}

export default App
>>>>>>> 6efc6930cd76932a9056d7757e89d81f03ae1cdf
