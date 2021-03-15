import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

const config = {
    withCredentials: true,
}

const Url = process.env.REACT_APP_SERVER_URL + '/api/v1/custom_tag';

// get 

export async function getTag() {
 
    const response = await axios.get(Url,config)
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

export async function createTag(tag) {
    console.log(tag);
   
    const response = await axios.post(Url,{
        "tag": tag
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

// delete Api 

export async function deleteTag(id) {
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