import * as API from '../api/StockTradingApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const GET_TRADING = 'GET_TRADING';
const GET_TRADING_ACYNC = 'GET_TRADING_ACYNC';
const CREATE_TRADING = 'CREATE_TRADING';
const UPDATE_TRADING = 'UPDATE_TRADING';
const DELETE_TRADING = 'DELETE_TRADING';
const GET_STOCK_BY_TICKER = 'GET_STOCK_BY_TICKER';

// Action Creator
export const getTrading = createAction(GET_TRADING, (year, month) => ({payload: {year, month}}));
export const getTradingAcync = createAction(GET_TRADING_ACYNC);
export const updateTrading = createAction(UPDATE_TRADING, (id, bookData) => ({payload: {id, bookData}}));
export const deleteTrading = createAction(DELETE_TRADING, (id) => ({payload: {id}}));
export const createTrading = createAction(CREATE_TRADING, (bookData) => ({payload: {bookData}}));
export const getStockByTicker = createAction(GET_STOCK_BY_TICKER, (ticker) => ({payload: {ticker}}));

// Main Saga
export function* tradingSaga() {
    yield takeEvery(GET_TRADING, getTradingSaga);
    yield takeEvery(CREATE_TRADING,createTradingSaga);
    yield takeEvery(DELETE_TRADING,deleteTradingSaga);
    yield takeEvery(UPDATE_TRADING,updateTradingSaga);
    yield takeEvery(GET_STOCK_BY_TICKER, stockByTickerSaga);
    //yield takeEvery(GET_MONEY_ACYNC, getMoneyAcyncSaga)
}

// getDate Saga
function* getTradingSaga({payload}) {
    const response = yield call(API.getStockTrading,payload.year, payload.month);
    yield put(getTradingAcync(response)) //put -> dispatch
}

// create Saga
function* createTradingSaga({payload}) {
    const response = yield call(API.createStockTrading, payload.bookData);
    console.log(response);
}

// update Saga
function* updateTradingSaga({payload}) {
    console.log(payload)
    const response = yield call(API.updateStockTrading, payload.id, payload.bookData);
    console.log(response);
}

// delete Saga
function* deleteTradingSaga({payload}) {
    const response = yield call(API.deleteStockTrading, payload.id);
    console.log(response);
}

// stock by ticker
function* stockByTickerSaga({payload}) {
    const response = yield call(API.getStockByTicker, payload.ticker);
    yield put(getTradingAcync(response))
    console.log(response);
}

/*function* getMoneyAcyncSaga() {
    const response = yield call(API.getMoneyBook);
    yield put(getMoney(response)) //put -> dispatch
}*/

// initState
const initialState = {
    tradingdata: []
};

// Toolkit Reducer
export default createReducer(initialState, {
    [GET_TRADING_ACYNC]: (state, {payload: data}) => {   
        state.tradingdata = data;
    },
    
})