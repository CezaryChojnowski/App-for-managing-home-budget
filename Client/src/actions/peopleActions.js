import axios from "axios";
import {GET_PEOPLE} from "./types";

export const getPeople = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/people/all");
    dispatch({type: GET_PEOPLE, payload: res.data});
};