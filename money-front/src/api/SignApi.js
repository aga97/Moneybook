import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

const config = {
    withCredentials: true,
}
const URL = process.env.REACT_APP_SERVER_URL;

//sign
// userA, 1111
export async function signIn(username, password) {
    const Url = URL + '/api/v1/login';
    console.log(username, password);

    
    const response = await axios.post(Url, {
        "username": username,
        "password": password
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

    console.log(response);
    return response.data;

    
}

// sign up

export async function signUp(signData) {
    const Url = URL + '/api/v1/join';
   
    const response = await axios.post(Url, {
        "username": signData.username,
        "password": signData.password,
        "email": signData.email
    }, config)
    .then((response) => {
        console.log(response);
        window.location.href = '/'; 
        return response;
    })
    .catch((error) => {
        if (error.response.status === (403 || 404)) {       
            window.location.href = '/';             
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            } else {                    
                console.log('Error', error.message);
            }
            console.log(error.response.data.message);
        });
    

   
}

//sign out 

export const signOut = async() => {
    const Url = URL + '/logout';
   
    const response = await axios.post(Url, null ,config)
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
    console.log(response);
    return response;

}