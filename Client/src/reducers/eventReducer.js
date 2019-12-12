import {GET_EVENTS, GET_EVENT, DELETE_EVENT} from "../actions/types";

const initialState = {
    events: [],
    event: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_EVENTS:
            return {
                ...state,
                events: action.payload
            };

        case GET_EVENT:
            return {
                ...state,
                event: action.payload
            };

        case DELETE_EVENT:
            return {
                ...state,
                events: state
                    .events
                    .filter(event => event.id !== action.payload)
            };

        default:
            return state;
    }
}