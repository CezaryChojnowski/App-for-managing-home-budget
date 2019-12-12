import axios from "axios";
import {GET_ERRORS, GET_EVENTS, DELETE_EVENT} from "./types";

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

export const deleteEvent = id => async dispatch => {
    {
        await axios.delete(`http://localhost:8080/events/${id}`);
        dispatch({type: DELETE_EVENT, payload: id});
    }
};