import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

//getDate

const config = {
    withCredentials: true,
}

const URL = process.env.REACT_APP_SERVER_URL;

export const getDate = async() => {

    const Url = URL + '/api/v1/date';
  
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
    
    const datelist = response.data;
    console.log(datelist);
    var dateObj = [];
    var monthArr = [];
    var i, j = 0;
    var index = 0;

    for (i = datelist.minyear; i <= datelist.maxyear; i++, index++) {
        // monthArr = monthArr.concat(i);
        if(datelist.minyear === datelist.maxyear) {
            for (j = datelist.minmonth; j <= datelist.maxmonth; j++) {
                monthArr = monthArr.concat(j);
            }    
            dateObj = [
                ...dateObj,
                {
                    year: i,
                    monthArr
                }
            ]
            break;        
        }

        if (i === datelist.minyear) {
            for (j = datelist.minmonth; j <= 12; j++) {
                monthArr = monthArr.concat(j);
            }
        } else if (i === datelist.maxyear) {
            for (j = 1; j <= datelist.maxmonth; j++) {
                monthArr = monthArr.concat(j);
            }
        } else {
            for (j = 1; j <= 12; j++) {
                monthArr = monthArr.concat(j);
            }
        }
        // dateObj[index] = monthArr;
        dateObj = [
            ...dateObj,
            {
                year: i,
                monthArr
            }
        ]
        monthArr = [];
    }
    console.log(dateObj)
    console.log(response)
    return dateObj;

    
}