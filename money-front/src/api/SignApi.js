import axios from 'axios';

//sign
// userA, 1111
export async function signIn(username, password) {
    const Url = 'http://localhost:8080//api/v1/login';
    console.log(username, password);

    try {
        const response = await axios.post(Url, {
            "username": username,
            "password": password
        });
        console.log(response);
        return response.data;

    } catch (error) {
        return error;
    }
}

// sign up

export async function signUp(username, password, email) {
    const Url = 'http://localhost:8080/api/v1/join';

    try {
        const response = await axios.post(Url, {
            "username": "userA",
            "password": 1111,
            "email": "email@email.com"
        });
        console.log(response);
        return response;

    } catch (error) {
        console.log(error.response.data);
        return error;
    }
}

//sign out 

export const signOut = async() => {
    const Url = 'http://localhost:8080//logout';
    try {
        const response = await axios.post(Url)
        console.log(response);
        return response;

    } catch (error) {
        return error;
    }
}