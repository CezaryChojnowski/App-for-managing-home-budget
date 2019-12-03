import axios from "axios";
import {GET_ERRORS} from "./types";
export const createCategory = (event, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8080/events", event);
        history.push("/getEvents");
    } catch (err) {
        dispatch({type: GET_ERRORS, payload: err.response.data});
    }
};