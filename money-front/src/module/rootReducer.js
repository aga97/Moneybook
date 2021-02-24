import { combineReducers } from 'redux';
import dateReducer, { dateSaga } from './dateReducer';
import moneyReducer, { moneySaga } from './moneyReducer';
import tagReducer, { tagSaga } from './tagReducer';
import stockTradingReducer, { tradingSaga } from './stockTradingReducer';
import stockPersonalReducer, { personalSaga } from './stockPersonalReducer';
import signReducer, { signSaga } from './signReducer';
import { all } from 'redux-saga/effects';

// boardReducer 를 rootReducer와 합쳐서 내보냄
const rootReducer = combineReducers({
    dateReducer,
    moneyReducer,
    tagReducer,
    stockTradingReducer,
    stockPersonalReducer,
    signReducer
});

// rootSaga는 generation function
export function* rootSaga() {
    // all 은 여러 사가를 동시에 실행.
    yield all([
        dateSaga(),
        moneySaga(),
        tagSaga(),
        tradingSaga(),
        personalSaga(),
        signSaga(),
    ]);
}

export default rootReducer;