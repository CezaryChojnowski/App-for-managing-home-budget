import {GET_SUBCATEGORIES, GET_SUBCATEGORY, DELETE_SUBCATEGORY} from "../actions/types";

const initialState = {
    subcategories: [],
    subcategory: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_SUBCATEGORIES:
            return {
                ...state,
                subcategories: action.payload
            };

        case GET_SUBCATEGORY:
            return {
                ...state,
                subcategory: action.payload
            };

        case DELETE_SUBCATEGORY:
            return {
                ...state,
                subcategories: state
                    .subcategories
                    .filter(subcategory => subcategory.id !== action.payload)
            };

        default:
            return state;
    }
}