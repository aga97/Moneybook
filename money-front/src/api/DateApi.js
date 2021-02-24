import axios from 'axios';

//getDate

export const getDate = async() => {
    const Url = '/api/v1/date';

    try {
        const response = await axios.get(Url);
        
        const datelist = response.data;
        console.log(datelist);
        var dateObj = [];
        var monthArr = [];
        var i, j = 0;
        var index = 0;

        for (i = datelist.minyear; i <= datelist.maxyear; i++, index++) {
            // monthArr = monthArr.concat(i);
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
        return dateObj;

    } catch (error) {
        return error;
    }
}