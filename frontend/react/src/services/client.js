import axios from 'axios';

export const getRecords = async() => {
    try{
       return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/records`)
    }catch(error){
        throw error;
    }
}