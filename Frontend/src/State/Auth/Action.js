import axios from "axios"
import {  LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionTypes";

export const register=(userData)=>async(dispatch)=>{

    dispatch({type:REGISTER_REQUEST})

    const baseUrl="http://localhost:5454"

    try{
        const response = await axios.post(`${baseUrl}/auth/signup`, userData);

        const user=response.data;
        console.log(user)

        dispatch({type:REGISTER_SUCCESS,payload:user.jwt});
        localStorage.setItem("jwt",user.jwt)
    }catch(error)
    {
        dispatch({type:REGISTER_FAILURE,payload:error.message})
        console.log(error);
    }
}

export const login=(userData)=>async(dispatch)=>{

    dispatch({type:LOGIN_REQUEST})

    const baseUrl="http://localhost:5454"

    try{
        const response=await axios.post(`${baseUrl}/auth/signin`,userData.data);
        const user=response.data;
        console.log(user)

        dispatch({type:LOGIN_SUCCESS,payload:user.jwt});
        localStorage.setItem("jwt",user.jwt)
        userData.navigate("/")
    }catch(error)
    {
        dispatch({type:LOGIN_FAILURE,payload:error.message})
        console.log(error);
    }
}

export const logout=()=>(dispatch)=>{
    localStorage.clear();
    dispatch({type:LOGOUT});
};