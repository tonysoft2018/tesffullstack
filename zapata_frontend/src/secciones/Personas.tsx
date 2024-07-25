import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import RowPersona from "../Components/RowPersona.tsx";
import { Person } from "../types/Person.tsx";

export default () => {
  const [personas, setPersonas] = useState([] as Person[]);
  const navigate = useNavigate();

  const handleDelete = (id: number) => {
    fetch(`http://localhost:8080/api/v1/person/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((_data) => {
        setPersonas(personas.filter((person) => person.id !== id));
      })
      .catch((error) => console.error("Error creating person:", error));
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/person")
      .then((res) => res.json())
      .then(({ data }) => setPersonas(data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Lista de Personas</h1>
      <button
        className="btn btn-primary mb-3"
        onClick={() => navigate("/crear")}
      >
        Crear Persona
      </button>
      {personas.length > 0 ? (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Puesto</th>
              <th>Sueldo</th>
              <th>Fecha de Nacimiento</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {personas.map((person) => (
              <RowPersona
                key={person.id}
                person={person}
                handleDelete={handleDelete}
              />
            ))}
          </tbody>
        </table>
      ) : (
        <p className="text-center alert alert-warning">
          No hay personas registradas
        </p>
      )}
    </div>
  );
};
