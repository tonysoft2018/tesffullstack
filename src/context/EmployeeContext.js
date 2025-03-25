import { useState, useEffect, createContext } from 'react';
import { EmployeesApi } from '../api/employes';

const employeeCtrl = new EmployeesApi();

export const EmployeesContext = createContext();

export const EmployeeProvider = (props) => {
    const { children } = props;

    const [dataEmployees, setDataEmployees] = useState([]);

    useEffect(() => {
        refreshEmployee();
    }, []);

    const handleDelete = async (id) => {
        await employeeCtrl.deleteEmployee(id);
        refreshEmployee();

    }

    const refreshEmployee = async () => {
        const response = await employeeCtrl.getAll();
        setDataEmployees(response);

    }

    const hanldeUpdate = async (id, data) => {
        await employeeCtrl.updateEmployee(id, data);
        refreshEmployee();
    }
    
    const handleRegistro = async(data) => {
        await employeeCtrl.registerEmployees(data);
        refreshEmployee();
    }

    const data ={
        dataEmployees,
        handleDelete,
        hanldeUpdate,
        refreshEmployee,
        handleRegistro
    }

    return <EmployeesContext.Provider value={ data }>{children}</EmployeesContext.Provider>
}