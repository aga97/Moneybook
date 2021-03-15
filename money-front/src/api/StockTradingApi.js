import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

const config = {
    withCredentials: true,
}

const Url = process.env.REACT_APP_SERVER_URL + '/api/v1/stock_trading';

// get 
export async function getStockTrading(year, month) {
   
    const getUrl = Url + '/' + year + '/' + month;
    
    const response = await axios.get(getUrl, config)
    .catch(function (error) {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
            console.log('Error', error.message);
            }
            console.log(error.config);
        });
    console.log(response)
    return response.data;
}

// create Api

export async function createStockTrading(tradingData) {

  
    const response = await axios.post(Url,{
        "ticker": tradingData.ticker,
        "price": tradingData.price,
        "stockquantity": tradingData.stockquantity,
        "year": tradingData.year,
        "month": tradingData.month,
        "day": tradingData.day,
    }, config)
    .catch(function (error) {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
            console.log('Error', error.message);
            }
            console.log(error.config);
        });
    
    console.log(response)
    return response.data;

}

// update Api

export async function updateStockTrading(id, tradingData) {
    const getUrl = Url + '/' + id;
    console.log(tradingData);
  
    const response = await axios.put(getUrl, {
        "price": tradingData.price,
        "stockquantity": tradingData.stockquantity,
        "year": tradingData.year,
        "month": tradingData.month,
        "day": tradingData.day,
    }, config)
    .catch(function (error) {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
            console.log('Error', error.message);
            }
            console.log(error.config);
        });
    return response.data;

}

// delete Api 

export async function deleteStockTrading(id) {
    const getUrl = Url + '/' + id;
    
    const response = await axios.delete(getUrl, config)
    .catch(function (error) {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
            console.log('Error', error.message);
            }
            console.log(error.config);
        });
    return response.data;   
}

// get stock by ticker

export async function getStockByTicker(ticker) {
    const getUrl = Url + '/' + ticker;
   
    const response = await axios.get(getUrl ,config)
    .catch(function (error) {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
            console.log('Error', error.message);
            }
            console.log(error.config);
        });
    return response.data;

}

