import * as API from '../api/SignApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const SIGN_IN = 'SIGN_IN';
const RELOAD = 'RELOAD';
const SIGN_OUT = 'SIGN_OUT';
const SIGN_OUT_RELOAD = 'SIGN_OUT_RELOAD';
const SIGN_UP = 'SIGN_UP';


// Action Creator
export const signIn = createAction(SIGN_IN, (username, password) => ({payload: {username, password}}));
export const reload = createAction(RELOAD, (username) => ({payload: {username}}));
export const signOut = createAction(SIGN_OUT);
export const signOutReload = createAction(SIGN_OUT_RELOAD);
export const signUp = createAction(SIGN_UP, (signData) => ({payload: {signData}}));


// Main Saga
export function* signSaga() {
    //yield takeEvery(GET_DATE_FUNC, getDateSaga);
    yield takeEvery(signIn, signInSaga)
    yield takeEvery(signOut, signOutSaga)
    yield takeEvery(signUp, signUpSaga)
}

// getDate Saga
function* signInSaga({payload}) {
    const response = yield call(API.signIn, payload.username, payload.password);
    yield put(reload(response)) //put -> dispatch
}

function* signOutSaga() {
    const response = yield call(API.signOut);
    yield put(signOutReload()) //put -> dispatch
}

function* signUpSaga({payload}) {
    const response = yield call(API.signUp, payload.signData);
}

// initState
const initialState = {

};

// Toolkit Reducer
export default createReducer(initialState, {
    [RELOAD]: (state, {payload: data}) => {       
        
        state.username = data.username;
    },
    [SIGN_OUT_RELOAD]: (state) => {       

        state.username = null;
    },

    
})