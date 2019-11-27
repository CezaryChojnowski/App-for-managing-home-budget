import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import securityReducer from "./securityReducer";
import walletReducer from "./walletReducer";
import categoryReducer from "./categoryReducer";

export default combineReducers({
  errors: errorReducer,
  wallet: walletReducer,
  security: securityReducer,
  category: categoryReducer
});