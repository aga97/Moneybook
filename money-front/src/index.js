import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import './index.css';
import App from './App';
import createSagaMiddleware from 'redux-saga';
import rootReducer, { rootSaga } from './module/rootReducer';
import { configureStore, createStore } from '@reduxjs/toolkit';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';

// Saga Middleware 생성
const sagaMiddleware = createSagaMiddleware();

// Store Saga Middleware 적용
// const store = createStore(rootReducer, applyMiddleware)
// Store Saga Middleware 적용 + redux devtool 적용
// const store = createStore(rootReducer, composeWithDevTools(applyMiddleware(sagaMiddleware)));

const store = configureStore({ reducer: rootReducer, middleware: [sagaMiddleware]})

// Saga 실행
sagaMiddleware.run(rootSaga);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
