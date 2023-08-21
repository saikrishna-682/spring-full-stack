import axios from 'axios';

export const getRecords = async() => {
    try{
       return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/records`)
    }catch(error){
        throw error;
    }
}

export const saveRecord = async(record) => {
    try{
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/records`,
                                 record);
    }catch(error){
    throw error;
    }
}

export const deleteRecord = async(id) => {
    try{
        return await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/api/v1/records/${id}`)
    }catch(err){
        throw err;
    }
}

export const updateRecord = async(id,update) =>{
    try{
        return await axios.put(`${import.meta.env.VITE_API_BASE_URL}/api/v1/records/${id}`,update)
    }catch(err){
    throw err;
    }
}