// Action.js
import axios from 'axios';
import { FETCH_CARS_REQUEST, FETCH_CARS_SUCCESS, FETCH_CARS_FAILURE } from './ActionTypes';

export const fetchCars = () => async (dispatch, getState) => {
    dispatch({ type: FETCH_CARS_REQUEST });
  
    try {
      // Retrieve the JWT token from the Redux state
      const jwt = getState().auth.jwt || localStorage.getItem("jwt");
      if (!jwt) {
        console.error("JWT token is missing or null");
        dispatch({ type: FETCH_CARS_FAILURE, payload: "Authentication token is missing" });
        return;
    }
      console.log("JWT Todfasfken:", jwt);

      const response = await axios.get('http://localhost:5454/api/cars/my-cars', {
        headers: {
          Authorization: `Bearer ${jwt}`,
          
        },
      });
  
      dispatch({
        type: FETCH_CARS_SUCCESS,
        payload: response.data, // Assuming response data is an array of cars
      });
    } catch (error) {
      dispatch({
        type: FETCH_CARS_FAILURE,
        payload: error.message,
      });
    }
  };
  
