import axios from 'axios';

const config = {
    withCredentials: true,
}

const Url = 'http://localhost:8080/v1/stock_personal';

// get 
export async function getStockPersonal() {

    try {
        const response = await axios.get(Url, config);
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// create Api

export async function createStockPersonal(personalData) {
    console.log(personalData);
    try {
        const response = await axios.post(Url,{
            "ticker": personalData.ticker,
            "targetquantity": personalData.targetquantity,
            "currentquantity": personalData.currentquantity
        }, config);
       
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// update Api

export async function updateStockPersonal(id, personalData) {
    const getUrl = Url + '/' + id;
    console.log(personalData)
    try {
        const response = await axios.put(getUrl, {
            "ticker": personalData.ticker,
            "targetquantity": personalData.targetquantity,
            "currentquantityweight": personalData.currentquantity
        }, config)
        return response.data;

    } catch (error) {
        return error;
    }
}

// delete Api 

export async function deleteStockPersonal(id) {
    const getUrl = Url + '/' + id;
    console.log(id);
    try {
        const response = await axios.delete(getUrl, config)
        return response.data;
    } catch (error) {
        return error;
    }
}