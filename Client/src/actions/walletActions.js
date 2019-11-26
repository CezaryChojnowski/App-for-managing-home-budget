import axios from "axios";
import { GET_ERRORS, GET_WALLETS, GET_WALLET } from "./types";

export const createWallet = (wallet, history) => async dispatch => {
  try {
    const res = await axios.post("http://localhost:8080/wallets", wallet);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

