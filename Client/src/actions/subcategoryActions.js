import axios from "axios";
import {GET_SUBCATEGORIES, GET_ERRORS, DELETE_SUBCATEGORY} from "./types";

export const getSubcategories = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/subcategories/all");
    dispatch({type: GET_SUBCATEGORIES, payload: res.data});
};

export const createSubcategory = (id, subcategory, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8080/categories/${id}/subcategories`, subcategory);
        history.push("/getCategories");
    } catch (err) {
        dispatch({type: GET_ERRORS, payload: err.response.data});
    }
};

export const deleteSubcategory = id => async dispatch => {
    {
        await axios.delete(`http://localhost:8080/subcategories/${id}`);
        dispatch({type: DELETE_SUBCATEGORY, payload: id});
    }
};

