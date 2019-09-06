import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import App from './App';
import './index.css';
import * as serviceWorker from './serviceWorker';
import catRankingsReducer from './store/catRankings/reducer';
import { CatRankingsState } from './store/catRankings/types';

const initialState: CatRankingsState = {
    catRankings: [],
    error: undefined,
    pending: false,
};

const store = createStore(catRankingsReducer, initialState, applyMiddleware(thunk));

ReactDOM.render(
    <Provider store={store}><App /></Provider>
    , document.getElementById('root')
    );

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
