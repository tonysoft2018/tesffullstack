import { useEffect, useState } from 'react'
import { Button } from '../components/Button'
import Table from '../components/Table'
import { Link } from 'react-router';
import { Person } from '../interfaces/Person';

const PERSONS_URL = 'http://localhost:8080/api/persons'

function Persons() {
  const [persons, setPersons] = useState<Person[]>([]);

  useEffect(() => {
    fetch(PERSONS_URL)
      .then(response => response.json())
      .then(json => {
        setPersons(json)
      })
  }, [])

  return (
    <>
      <h1>Personas</h1>
      <Table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Fecha de nacimiento</th>
            <th>Position</th>
            <th>Salario</th>
            <th>
              <Link to="/create">
                <Button>Nueva persona</Button>
              </Link>
            </th>
          </tr>
        </thead>
        <tbody>
          {persons.map(person => (
            <tr key={person.id}>
              <td>{person.id}</td>
              <td>{person.name}</td>
              <td>{person.lastname}</td>
              <td>{person.birth_date}</td>
              <td>{person.position}</td>
              <td>{person.salary}</td>
              <td>
                <div style={{ display: "flex", gap: "0.3rem" }}>
                  <Link to={'/editar/' + person.id}>
                    <Button $bgColor='#dfba00'>Editar</Button>
                  </Link>
                  <Button $bgColor="#ff4545">Eliminar</Button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  )
}

export default Persons