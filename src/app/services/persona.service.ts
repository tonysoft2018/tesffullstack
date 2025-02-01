import { Injectable } from '@angular/core';
import { Persona } from '../models/persona.model';

@Injectable({
  providedIn: 'root',
})
export class PersonaService {
  private personas: Persona[] =[];

  constructor() {}

  getPersonas(): Persona[] {
    return this.personas;
  }

  agregarPersona(persona: Persona): void {
    this.personas.push(persona);
  }

  eliminarPersona(id: number): void {
    this.personas = this.personas.filter((u) => u.id !== id);
  }

  editarPersona(persona: Persona): void {
    const index = this.personas.findIndex((u) => u.id === persona.id);
    if (index !== -1) {
      this.personas[index] = persona;
    }
  }
}
