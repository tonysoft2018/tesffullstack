
export interface Response<T> {
  data: T;
  message?: string;
}

export interface Person {
  id: number;
  nombre: string;
  apellido: string;
  fechaNacimiento: string;
  puesto: string;
  sueldo: number;
}