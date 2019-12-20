import axios from "axios";
import {GET_TRANSACTIONS, GET_ERRORS, DELETE_TRANSACTION} from "./types";

export const getTransactions = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/transactions/all");
    dispatch({type: GET_TRANSACTIONS, payload: res.data});
};

export const createTransaction = (event, person, wallet, subcategory ,transaction, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8080/transactions`, transaction, {params: {
            event,
            person,
            wallet,
            subcategory
        }});
        history.push("/getTransactions");
    } catch (err) {
        dispatch({type: GET_ERRORS, payload: err.response.data});
    }
};

export const getEventTransactions = (id, history) => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8080/transactions/${id}`);
        dispatch({type: GET_TRANSACTIONS, payload: res.data});
    } catch (error) {
        history.push("/dashboard");
    }
};

export const deleteTransaction = id => async dispatch => {
    {
        await axios.delete(`http://localhost:8080/transactions/${id}`);
        dispatch({type: DELETE_TRANSACTION, payload: id});
    }
};

// export const getStats = () => async dispatch => {
//     axios.get(`http://localhost:8080/transactions/stats`).then(res => {const data = res.data
//     });
//     console.log(response);
// };