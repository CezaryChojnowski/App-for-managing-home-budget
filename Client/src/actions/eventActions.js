import axios from "axios";
import {GET_ERRORS, GET_EVENTS} from "./types";

export const createEvent = (event, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8080/events", event);
        history.push("/getEvents");
    } catch (err) {
        dispatch({type: GET_ERRORS, payload: err.response.data});
    }
};

export const getEvents = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/events/all");
    dispatch({type: GET_EVENTS, payload: res.data});
};