import { thunk } from "redux-thunk";
import authReducer from "./Auth/Reducer";
import { combineReducers, legacy_createStore, applyMiddleware }  from "redux";
import carReducer from "./MyCars/Reducer";

const rootReducer=combineReducers({
    auth:authReducer,
    cars:carReducer,
});

export const store=legacy_createStore(rootReducer,applyMiddleware(thunk))