import { FETCH_TODOS, ADD_TODO, REMOVE_TODO, TOGGLE_TODO } from '../actions';
const initialState = {
    todos: [
        {
            id: 0,
            text: '',
            checked: false,
        }
    ]
}
export const toDoReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_TODOS:
            return Object.assign({}, state, { todos: action.payload });
        case ADD_TODO:
            return Object.assign({}, state, { todos: action.payload });
        case REMOVE_TODO:
            return Object.assign({}, state, { todos: action.payload });
        case TOGGLE_TODO:
            return Object.assign({}, state, { todos: action.payload });
        default:
            return state;
    }
}