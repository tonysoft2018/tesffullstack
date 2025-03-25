import { useNavigate } from 'react-router-dom';
import { Button } from '../../components'
import { useEmployees } from '../../hooks/useEmployees';

export const ListEmployees = () => {

    const { dataEmployees, handleDelete } = useEmployees();
    const navigate = useNavigate();
    


    return (
        <div>
            <h1>Listado de empleados</h1>

            {
                dataEmployees.length === 0 ? 
                    <div> Sin datos de empleados</div> :
                    <table>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Fecha Nacimiento</th>
                                <th>Puesto</th>
                                <th>Sueldo</th>
                                
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                dataEmployees?.map( employee => (
                                    <tr key={ employee.id }>
                                        <td> { employee.firtsName } { employee.lastName} </td>
                                        <td> { employee.birthDate } </td>
                                        <td> { employee.position } </td>
                                        <td> { employee.salary } </td>
                                        <td>
                                            <div>
                                                <Button 
                                                    text="Editar"
                                                    handleClick={ () => navigate('/crear', {
                                                        state: {
                                                            isCreated: false,
                                                            id: employee.id,
                                                            dataEmployed: {...employee}
                                                        }
                                                    })}
                                                    style={{
                                                        backgroundColor: '#D8820C',
                                                        color: 'black',
                                                        marginLeft: "10px"
                                                    }}
                                                />
                                                <Button 
                                                    text="Eliminar"
                                                    handleClick={ () => handleDelete(employee.id)}
                                                    style={{
                                                        backgroundColor: 'red',
                                                        color: 'black',
                                                        marginLeft: "15px"
                                                    }}
                                                />
                                            </div>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
            }
            

            <div>
                <Button 
                    text="Agregar Empleados"
                    handleClick={ () => navigate('/crear', {
                        state: {
                            isCreated: true
                        }
                    })}
                />
            </div>
        </div>
    )
}
