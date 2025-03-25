import { useContext } from "react";
import { EmployeesContext } from "../context/EmployeeContext";


export const useEmployees = () => useContext(EmployeesContext);