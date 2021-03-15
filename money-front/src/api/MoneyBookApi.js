import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

const config = {
    withCredentials: true,
}

const Url = process.env.REACT_APP_SERVER_URL + '/api/v1/moneybook';

// get 
export async function getMoneyBook(year, month) {
    // 실제 api 할때
    const getUrl = Url + '/' + year + '/' + month;
    //const getUrl = Url;
    
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
    const moneybook = response.data;

    console.log(response)
    return moneybook;
    
}

// create Api

export async function createMoneyBook(bookData) {

  
    const response = await axios.post(Url, {
        "year": bookData.year,
        "month": bookData.month,
        "day": bookData.day,
        "context": bookData.context,
        "amount": bookData.amount,
        "tag": bookData.tag
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

export async function updateMoneyBook(id, bookData) {
    const getUrl = Url + '/' + id;

 
    const response = await axios.put(getUrl, {
        "year": bookData.year,
        "month": bookData.month,
        "day": bookData.day,
        "context": bookData.context,
        "amount": bookData.amount,
        "tag": bookData.tag
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

export async function deleteMoneyBook(id) {
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

// get by tag

export async function getMoneyBookByTag(tag) {
    const getUrl = Url + '/' + tag;
    
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
    return response.data;
    
}
