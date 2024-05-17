import { createStore } from 'redux';

const initialState = {
    lock: false
}

const reducer = (state = initialState, action) => {
    switch (action.type){
        case 'Lock':
            return {
                ...state, lock: true
            }
        case 'Unlock':
            return {
                ...state, lock: false
            }
        default:
            return state;
    }
}


const store  = createStore(reducer);

export default store;