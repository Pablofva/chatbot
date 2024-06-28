import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const registerUser = async (userData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/users/add`, userData);
        return response.data;
    } catch (error) {
        console.error('Error registering user:', error);
        throw error;
    }
};
export const getUserById = async (userId) => {
    try {
        console.log('Peticion getUserById con userId:', userId); // Agrega este log
        const response = await axios.get(API_BASE_URL + '/users/user/' + userId);
        return response.data;
    } catch (error) {
        // Registrar el objeto de error completo en la consola
        console.error('Error fetching user data:', error);
        throw error; // Re-lanzar el error para que pueda ser manejado en el componente
    }
};


export const analyzeText = async (userId, text) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/chatbot/analyze/${userId}`, text);
        return response.data;
    } catch (error) {
        console.error('Error analyzing text:', error);
        throw error;
    }
};
