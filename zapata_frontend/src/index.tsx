import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import CreatePerson from "./secciones/CreatePerson.tsx";
import Personas from "./secciones/Personas.tsx";
import "./styles.css";

const router = createBrowserRouter([
  { path: "/", element: <Personas /> },
  { path: "/crear", element: <CreatePerson /> },
  { path: "/editar/:id", element: <CreatePerson /> },
]);

import React from "react";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement as HTMLElement);

root.render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
