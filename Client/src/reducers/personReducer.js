import {GET_PEOPLE, GET_PERSON} from "../actions/types";

const initialState = {
    people: [],
    person: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_PEOPLE:
            return {
                ...state,
                people: action.payload
            };

        case GET_PERSON:
            return {
                ...state,
                person: action.payload
            };

        default:
            return state;
    }
}