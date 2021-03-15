import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

const config = {
    withCredentials: true,
}

const Url = process.env.REACT_APP_SERVER_URL + '/api/v1/stock_personal';

// get 
export async function getStockPersonal() {

   
    const response = await axios.get(Url, config)
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

export async function createStockPersonal(personalData) {
    console.log(personalData);
   
    const response = await axios.post(Url,{
        "ticker": personalData.ticker,
        "targetquantity": personalData.targetquantity,
        "currentquantity": personalData.currentquantity
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

export async function updateStockPersonal(id, personalData) {
    const getUrl = Url + '/' + id;
    console.log(personalData)
    
    const response = await axios.put(getUrl, {
        "ticker": personalData.ticker,
        "targetquantity": personalData.targetquantity,
        "currentquantityweight": personalData.currentquantity
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

export async function deleteStockPersonal(id) {
    const getUrl = Url + '/' + id;
    console.log(id);
    
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
