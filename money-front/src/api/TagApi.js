import axios from 'axios';

const config = {
    withCredentials: true,
}

const Url = 'http://localhost:8080/api/v1/custom_tag';

// get 

export async function getTag() {

    try {
        const response = await axios.get(Url,config);
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// create Api

export async function createTag(tag) {
    console.log(tag);
    try {
        const response = await axios.post(Url,{
            "tag": tag
        }, config);
       
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// delete Api 

export async function deleteTag(id) {
    const getUrl = Url + '/' + id;
    try {
        const response = await axios.delete(getUrl, config);
        return response.data;
    } catch (error) {
        return error;
    }
}