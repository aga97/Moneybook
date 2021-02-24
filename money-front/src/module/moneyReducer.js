import * as API from '../api/MoneyBookApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const GET_MONEY = 'GET_MONEY';
const GET_MONEY_ACYNC = 'GET_MONEY_ACYNC';
const CREATE_MONEY = 'CREATE_MONEY';
const UPDATE_MONEY = 'UPDATE_MONEY';
const DELETE_MONEY = 'DELETE_MONEY';

// Action Creator
export const getMoney = createAction(GET_MONEY, (year, month) => ({payload: {year, month}}));
export const getMoneyAcync = createAction(GET_MONEY_ACYNC);
export const updateMoney = createAction(UPDATE_MONEY, (id, bookData) => ({payload: {id, bookData}}));
export const deleteMoney = createAction(DELETE_MONEY, (id) => ({payload: {id}}));
export const createMoney = createAction(CREATE_MONEY, (bookData) => ({payload: {bookData}}));

// Main Saga
export function* moneySaga() {
    yield takeEvery(GET_MONEY, getMoneySaga);
    yield takeEvery(CREATE_MONEY,createMoneySaga);
    yield takeEvery(DELETE_MONEY,deleteMoneySaga);
    yield takeEvery(UPDATE_MONEY,updateMoneySaga);
    //yield takeEvery(GET_MONEY_ACYNC, getMoneyAcyncSaga)
}

// getDate Saga
function* getMoneySaga({payload}) {
    const response = yield call(API.getMoneyBook,payload.year, payload.month);
    yield put(getMoneyAcync(response)) //put -> dispatch
}

// create Saga
function* createMoneySaga({payload}) {
    const response = yield call(API.createMoneyBook, payload.bookData);
    console.log(response);
}

// update Saga
function* updateMoneySaga({payload}) {
    const response = yield call(API.updateMoneyBook, payload.id, payload.bookData);
    console.log(response);
}

// delete Saga
function* deleteMoneySaga({payload}) {
    const response = yield call(API.deleteMoneyBook, payload.id);
    console.log(response);
}


/*function* getMoneyAcyncSaga() {
    const response = yield call(API.getMoneyBook);
    yield put(getMoney(response)) //put -> dispatch
}*/

// initState
const initialState = {
};

// Toolkit Reducer
export default createReducer(initialState, {
    [GET_MONEY_ACYNC]: (state, {payload: moneydate}) => {   
        state.moneydate = moneydate;
    },
    
})