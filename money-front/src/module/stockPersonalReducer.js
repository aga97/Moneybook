import * as API from '../api/StockPersonalApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const GET_PERSONAL = 'GET_PERSONAL';
const GET_PERSONAL_ACYNC = 'GET_PERSONAL_ACYNC';
const CREATE_PERSONAL = 'CREATE_PERSONAL';
const UPDATE_PERSONAL = 'UPDATE_PERSONAL';
const DELETE_PERSONAL = 'DELETE_PERSONAL';

// Action Creator
export const getPersonal = createAction(GET_PERSONAL);
export const getPersonalAcync = createAction(GET_PERSONAL_ACYNC);
export const updatePersonal = createAction(UPDATE_PERSONAL, (id, personalData) => ({payload: {id, personalData}}));
export const deletePersonal = createAction(DELETE_PERSONAL, (id) => ({payload: {id}}));
export const createPersonal = createAction(CREATE_PERSONAL, (personalData) => ({payload: {personalData}}));

// Main Saga
export function* personalSaga() {
    yield takeEvery(GET_PERSONAL, getPersonalSaga);
    yield takeEvery(CREATE_PERSONAL,createPersonalSaga);
    yield takeEvery(DELETE_PERSONAL,deletePersonalSaga);
    yield takeEvery(UPDATE_PERSONAL,updatePersonalSaga);
    //yield takeEvery(GET_MONEY_ACYNC, getMoneyAcyncSaga)
}

// getDate Saga
function* getPersonalSaga({payload}) {
    const response = yield call(API.getStockPersonal);
    yield put(getPersonalAcync(response)) //put -> dispatch
}

// create Saga
function* createPersonalSaga({payload}) {
    const response = yield call(API.createStockPersonal, payload.personalData);
    console.log(response);
}

// update Saga
function* updatePersonalSaga({payload}) {
    console.log(payload)
    const response = yield call(API.updateStockPersonal, payload.id, payload.personalData);
    console.log(response);
}

// delete Saga
function* deletePersonalSaga({payload}) {
    const response = yield call(API.deleteStockPersonal, payload.id);
    console.log(response);
}

/*function* getMoneyAcyncSaga() {
    const response = yield call(API.getMoneyBook);
    yield put(getMoney(response)) //put -> dispatch
}*/

// initState
const initialState = {
    personaldata: []
};

// Toolkit Reducer
export default createReducer(initialState, {
    [GET_PERSONAL_ACYNC]: (state, {payload: data}) => {   
        state.personaldata = data;
    },
    
})