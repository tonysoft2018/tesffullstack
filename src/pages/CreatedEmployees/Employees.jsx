import { useNavigate, useLocation } from 'react-router-dom';
import { useFormik } from "formik"
import { Button,TextField } from "../../components"


import { validateForms } from "../../utils/validate/employees"
import { EmployeesApi } from "../../api/employes";

import '../../theme/Employees/Employee.css';
import { useEmployees } from '../../hooks/useEmployees';


const employesCrtl = new EmployeesApi();

export const Employees = () => {

    const { hanldeUpdate, handleRegistro } = useEmployees();
    const navigate = useNavigate();
    const location = useLocation();
    const data = location.state?.dataEmployed;

    const formik = useFormik({
        initialValues: location.state.isCreated ? {
            nombre: '',
            apellido: '',
            fechanac: '',
            puesto: '',
            sueldo: ''
        } : {
            nombre: data.firtsName,
            apellido: data.lastName,
            fechanac: data.birthDate,
            puesto: data.position,
            sueldo: data.salary
        },
        validationSchema: validateForms(),
        validateOnChange: false,
        onSubmit: async (values) => {
            try {
                const data = {
                    "firtsName": values.nombre,
                    "lastName": values.apellido,
                    "birthDate": values.fechanac,
                    "position": values.puesto,
                    "salary": parseFloat(values.sueldo)
                }
                if (location.state.isCreated) {
                    handleRegistro(data)
                }else{
                    const idEmployed = location.state.id;
                    hanldeUpdate(idEmployed, data)
                }
                
            } catch (error) {
                console.log(error);
            }
        }
    })


    return (
        <div className="containerForm">
            <form className="containerForm-items" onSubmit={ formik.handleSubmit }>
                <TextField 
                    style={{
                        width: "240px",
                        height: '25px',
                    }}
                    name="nombre"
                    handleChange={ formik.handleChange}
                    placeholder='Ingresa el Nombre'
                    value={ formik.values.nombre }
                    errors={ formik.errors.nombre }
                />
                <TextField 
                    style={{
                        width: "240px",
                        height: '25px',
                    }}
                    name="apellido"
                    handleChange={ formik.handleChange}
                    placeholder='Ingresa el Apellido'
                    value={ formik.values.apellido }
                    errors={ formik.errors.apellido }
                />
                <TextField 
                    style={{
                        width: "240px",
                        height: '25px',
                    }}
                    name="fechanac"
                    handleChange={ formik.handleChange}
                    placeholder='Fecha de nacimiento (1997-01-01)'
                    value={ formik.values.fechanac }
                    errors={ formik.errors.fechanac }
                />
                <TextField 
                    style={{
                        width: "240px",
                        height: '25px',
                    }}
                    name="puesto"
                    handleChange={ formik.handleChange}
                    placeholder='Ingresa el puesto'
                    value={ formik.values.puesto }
                    errors={ formik.errors.puesto }
                />
                <TextField 
                    style={{
                        width: "240px",
                        height: '25px',
                    }}
                    name="sueldo"
                    handleChange={ formik.handleChange}
                    placeholder='Ingresa el Sueldo'
                    value={ formik.values.sueldo }
                    errors={ formik.errors.sueldo }
                />

                <div>
                    <Button
                        type='submit'
                        text={location.state.isCreated ? 'Registrar' : 'Actualizar'}
                    />

                    <Button
                        text='Regresar'
                        handleClick={ () => navigate(-1)}
                        style={{
                            backgroundColor: 'yellow',
                            color: 'black',
                            marginLeft: "10px"
                        }}
                    />
                </div>
                
            </form>
            
           
        </div>
    )
}
