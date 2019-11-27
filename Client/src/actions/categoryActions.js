import axios from "axios";
import { GET_ERRORS, GET_CATEGORIES } from "./types";

export const createCategory = (category, history) => async dispatch => {
  try {
    const res = await axios.post("http://localhost:8080/categories", category);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getCategories = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/categories/all");
  dispatch({
    type: GET_CATEGORIES,
    payload: res.data
  });
};


