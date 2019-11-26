import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import securityReducer from "./securityReducer";
import walletReducer from "./walletReducer";

export default combineReducers({
  errors: errorReducer,
  wallet: walletReducer,
  security: securityReducer
});