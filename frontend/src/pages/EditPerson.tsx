import { useEffect, useState } from 'react';
import { Button } from '../components/Button'
import { Link, useParams } from 'react-router';
import { Person } from '../interfaces/Person';

const PERSONS_URL = "http://localhost:8080/api/persons";

function EditPerson() {
  const { id } = useParams();

  const [person, setPerson] = useState<Person>();

  const [formData, setFormData] = useState({
    name: person?.name,
    lastname: person?.lastname,
    position: person?.position,
    birth_date: person?.birth_date,
    salary: person?.salary,
  });

  useEffect(() => {
    fetch(`${PERSONS_URL}/${id}`)
      .then(response => response.json())
      .then(data => setPerson(data))
      .catch(error => console.error("Error:", error));
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const submitHandler = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch(`${PERSONS_URL}/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Error al registrar la persona");
      }

      alert("Se registró el usuario");
    } catch (error: any) {
      alert(error.message);
    }
  };

  return (
    <>
      <h1>Editar persona</h1>
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column", gap: "1rem" }}
      >
        <div style={{ display: "flex", gap: "0.5rem" }}>
          <div>
            <label style={{ display: "block" }} htmlFor="name">
              Nombre
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label style={{ display: "block" }} htmlFor="lastname">
              Apellidos
            </label>
            <input
              type="text"
              name="lastname"
              value={formData.lastname}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div style={{ display: "flex", gap: "0.5rem" }}>
          <div>
            <label style={{ display: "block" }} htmlFor="position">
              Puesto
            </label>
            <input
              type="text"
              name="position"
              value={formData.position}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label style={{ display: "block" }} htmlFor="birth_date">
              Fecha de nacimiento
            </label>
            <input
              style={{ width: "100%" }}
              type="date"
              name="birth_date"
              value={formData.birth_date}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <label htmlFor="salary">Salario</label>
        <input
          type="number"
          name="salary"
          value={formData.salary}
          onChange={handleChange}
          required
        />

        <Button type="submit">Guardar</Button>
        <Link to="/">
          <Button type="button" $bgColor="#8b8b8b">Cancelar</Button>
        </Link>
      </form>
    </>
  )
}

export default EditPerson