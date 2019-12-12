import {GET_CATEGORIES, GET_CATEGORY, DELETE_CATEGORY, DELETE_SUBCATEGORY} from "../actions/types";

const initialState = {
    categories: [],
    category: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_CATEGORIES:
            return {
                ...state,
                categories: action.payload
            };

        case GET_CATEGORY:
            return {
                ...state,
                category: action.payload
            };

        case DELETE_CATEGORY:
            return {
                ...state,
                categories: state
                    .categories
                    .filter(category => category.id !== action.payload)
            };

        default:
            return state;
    }
}