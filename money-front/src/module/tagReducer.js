import * as API from '../api/TagApi';
import { call, put, takeEvery } from 'redux-saga/effects';
import { createAction, createReducer } from '@reduxjs/toolkit';

// Action Type
const GET_TAG = 'GET_TAG';
const CREATE_TAG = 'CREATE_TAG';
const DELETE_TAG = 'DELETE_TAG';
const GET_TAG_ACYNC = 'GET_TAG_ACYNC';

// Action Creator
export const getTag = createAction(GET_TAG);
export const createTag = createAction(CREATE_TAG, (tag) => ({payload: {tag}}));
export const deleteTag = createAction(DELETE_TAG, (id) => ({payload: {id}}));
export const getTagAcync = createAction(GET_TAG_ACYNC);

// Main Saga
export function* tagSaga() {
    yield takeEvery(GET_TAG, getTagSaga);
    yield takeEvery(CREATE_TAG,createTagSaga);
    yield takeEvery(DELETE_TAG,deleteTagSaga);
    //yield takeEvery(GET_MONEY_ACYNC, getMoneyAcyncSaga)
}

// getDate Saga
function* getTagSaga() {
    const response = yield call(API.getTag);
    yield put(getTagAcync(response)) //put -> dispatch
}

// create Saga
function* createTagSaga({payload}) {
    const response = yield call(API.createTag, payload.tag);
    console.log(response);
}

// delete Saga
function* deleteTagSaga({payload}) {
    const response = yield call(API.deleteTag, payload.id);
    console.log(response);
}

// initState
const initialState = {
    tags:[]
};

// Toolkit Reducer
export default createReducer(initialState, {
    [GET_TAG_ACYNC]: (state, {payload: data}) => {   
        state.tags = data
    },
})