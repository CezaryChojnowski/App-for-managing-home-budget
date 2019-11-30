import axios from "axios";
import {GET_SUBCATEGORIES} from "./types";

export const getSubcategories = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/subcategories/all");
    dispatch({type: GET_SUBCATEGORIES, payload: res.data});
};
