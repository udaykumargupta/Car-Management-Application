import {Route, Routes } from "react-router-dom";
import Auth from "./page/Auth/Auth";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUser } from "./State/Auth/Action";
import Home from "./page/home/Home";
import Navbar from "./page/Navbar/Navbar";
import AddCars from "./page/AddCars/AddCars";


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
          <Navbar/>
          <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/addcars" element={<AddCars/>}/>
          </Routes>
        </div>
      ):<Auth/>}
    </>
  );
}

export default App;
