import { GET_SUBCATEGORIES, GET_SUBCATEGORY } from "../actions/types";

const initialState = {
  subcategories: [],
  subcategory: {}
};

export default function(state = initialState, action) {
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

    default:
      return state;
  }
}