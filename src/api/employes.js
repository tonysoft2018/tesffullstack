import axios from 'axios';
import { ENV } from '../utils/constant';


export class EmployeesApi {

    async getAll() {
        try {
            const url = `${ENV.API_URL}/${ENV.ENDPOINTS.FIND}`;
            const response = await axios.get(url);

            if (response.status !== 200) throw response;

            return response.data;
        } catch (error) {
            throw error;
        }
    }

    async registerEmployees(data){
        try {
            const url = `${ENV.API_URL}/${ENV.ENDPOINTS.CREATED}`;
            const response = await axios.post( url, {
                ...data
            });

            if (response.status !== 201) throw response;

            return response.data;

        } catch (error) {
            throw error;
        }
    }


    async updateEmployes(data){
        try {
            const url = `${ENV.API_URL}/${ENV.ENDPOINTS.UPDATE}`;
            const response = await axios.post( url, {
                ...data
            });

            if (response.status !== 200) throw response;

            return response.data;

        } catch (error) {
            throw error;
        }
    }

    async deleteEmployee(id){
        try {
            const url = `${ENV.API_URL}/${ENV.ENDPOINTS.DELETE}/${id}`;
            const response = await axios.delete(url);

            if (response.status !== 200) throw response;

            return response.data;

        } catch (error) {
            console.error(error);
        }
    }

    async updateEmployee(id, data){
        try {
            const url = `${ENV.API_URL}/${ENV.ENDPOINTS.UPDATE}/${id}`;
            const response = await axios.put(url, {
                ...data
            })

            if (response.status !== 200) throw response;

            return response.data;

        } catch (error) {
            console.error(error);
        }
    }

}