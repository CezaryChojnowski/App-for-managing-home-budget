import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import backlogReducer from "./backlogReducer";
import securityReducer from "./securityReducer";
import walletReducer from "./walletReducer";

export default combineReducers({
  errors: errorReducer,
  wallet: walletReducer,
  backlog: backlogReducer,
  security: securityReducer
});