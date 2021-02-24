import * as API from '../api/DateApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const GET_DATE_FUNC = 'GET_DATE_FUNC';
const GET_DATE_ACYNC = 'GET_DATE_ASYNC';
const TIME_REV = 'TIME_REV';

// Action Creator
export const getDateFunc = createAction(GET_DATE_FUNC, (date) => ({payload: {date}}));
export const getDateAcync = createAction(GET_DATE_ACYNC);
export const timeRev = createAction(TIME_REV, (year, month) => ({payload: {year, month}}));

// Main Saga
export function* dateSaga() {
    //yield takeEvery(GET_DATE_FUNC, getDateSaga);
    yield takeEvery(GET_DATE_ACYNC, getDateAcyncSaga)
}

// getDate Saga
function* getDateAcyncSaga() {
    const response = yield call(API.getDate);
    yield put(getDateFunc(response)) //put -> dispatch
}

// year, month 


// initState
const initialState = {
    time:{
        year: null,
        month: null,
    }
};

// Toolkit Reducer
export default createReducer(initialState, {
    [GET_DATE_FUNC]: (state, {payload: date}) => {       
        state.date = date;
    },
    [TIME_REV]: (state, {payload: date}) => {
        state.time.year = date.year
        state.time.month = date.month
    }
})