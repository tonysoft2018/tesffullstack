import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Person } from "../types/Person.tsx";

export default () => {
  const [persona, setPersona] = useState({} as Person);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/api/v1/person/${id}`)
        .then((res) => res.json())
        .then(({ data: [person] }) => {
          if (!person) navigate("/");
          setPersona(person);
        })
        .catch((error) => {
          console.error(error);
          navigate("/");
        });
    }
  }, [id]);

  const handleChange = ({ target }) => {
    const { name, value } = target;
    setPersona({
      ...persona,
      [name]: value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const url = id
      ? `http://localhost:8080/api/v1/person/${id}`
      : "http://localhost:8080/api/v1/person";

    fetch(url, {
      method: id ? "PUT" : "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(persona),
    })
      .then((response) => response.json())
      .then((_data) => {
        navigate("/");
      })
      .catch((error) => console.error("Error creating person:", error));
  };

  return (
    <div className="container mt-5 w-50">
      <h1>{id ? "Editar Persona" : "Crear Persona"}</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre</label>
          <input
            type="text"
            className="form-control"
            name="nombre"
            value={persona.nombre}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Apellido</label>
          <input
            type="text"
            className="form-control"
            name="apellido"
            value={persona.apellido}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Puesto</label>
          <input
            type="text"
            className="form-control"
            name="puesto"
            value={persona.puesto}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Sueldo</label>
          <input
            type="number"
            className="form-control"
            name="sueldo"
            value={persona.sueldo}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Fecha de Nacimiento</label>
          <input
            type="date"
            className="form-control"
            name="fechaNacimiento"
            value={persona.fechaNacimiento}
            onChange={handleChange}
            required
          />
        </div>
        <div className="d-flex justify-content-between mt-3">
          <button type="submit" className="btn btn-primary">
            {id ? "Actualizar" : "Crear"}
          </button>
          <button
            type="submit"
            className="btn btn-warning"
            onClick={() => navigate("/")}
          >
            Cancelar
          </button>
        </div>
      </form>
    </div>
  );
};
