import axios from 'axios';

const Url = '/api/v1/custom_tag';

// get 

export async function getTag() {

    try {
        const response = await axios.get(Url);
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
        });
       
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
        const response = await axios.delete(getUrl);
        return response.data;
    } catch (error) {
        return error;
    }
}