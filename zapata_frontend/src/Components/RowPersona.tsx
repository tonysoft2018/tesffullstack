import React from "react";
import { useNavigate } from "react-router-dom";

export default ({ person, handleDelete }) => {
  const navigate = useNavigate();
  return (
    <tr>
      <td>{person.id}</td>
      <td>{person.nombre}</td>
      <td>{person.apellido}</td>
      <td>{person.puesto}</td>
      <td>{person.sueldo}</td>
      <td>{person.fechaNacimiento}</td>
      <td className="d-flex justify-content-around">
        <button
          className="btn btn-warning btn-sm mr-2"
          onClick={() => navigate(`/editar/${person.id}`)}
        >
          Editar
        </button>
        <button
          className="btn btn-danger btn-sm mr-2"
          onClick={() => handleDelete(person.id)}
        >
          Eliminar
        </button>
      </td>
    </tr>
  );
};
