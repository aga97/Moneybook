import axios from 'axios';

const config = {
    withCredentials: true,
}

const Url = 'http://localhost:8080/api/v1/moneybook';

// get 
export async function getMoneyBook(year, month) {
    // 실제 api 할때
    const getUrl = Url + '/' + year + '/' + month;
    //const getUrl = Url;
    try {
        const response = await axios.get(getUrl, config);
        const moneybook = response.data;

        console.log(response)
        return moneybook;
    } catch (error) {
        return error;
    }
}

// create Api

export async function createMoneyBook(bookData) {

    try {
        const response = await axios.post(Url, {
            "year": bookData.year,
            "month": bookData.month,
            "day": bookData.day,
            "context": bookData.context,
            "amount": bookData.amount,
            "tag": bookData.tag
        }, config)

        console.log(response)

        return response.data;
    } catch (error) {
        return error;
    }

}

// update Api

export async function updateMoneyBook(id, bookData) {
    const getUrl = Url + '/' + id;

    try {
        const response = await axios.put(getUrl, {
            "year": bookData.year,
            "month": bookData.month,
            "day": bookData.day,
            "context": bookData.context,
            "amount": bookData.amount,
            "tag": bookData.tag
        }, config)
        return response.data;

    } catch (error) {
        return error;
    }



}

// delete Api 

export async function deleteMoneyBook(id) {
    const getUrl = Url + '/' + id;
    try {
        const response = await axios.delete(getUrl, config);
        return response.data;
    } catch (error) {
        return error;
    }

}

// get by tag

export async function getMoneyBookByTag(tag) {
    const getUrl = Url + '/' + tag;
    try {
        const response = await axios.get(getUrl);
        return response.data;
    } catch (error) {
        return error
    }
}