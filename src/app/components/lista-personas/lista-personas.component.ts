import { Component, OnInit } from '@angular/core';
import { Persona } from '../../models/persona.model';
import { ApiService } from '../../services/api.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-lista-personas',
  templateUrl: './lista-personas.component.html',
  styleUrls: ['./lista-personas.component.css'],
  imports: [
    NgForOf
  ],
  standalone: true
})
export class ListaPersonasComponent implements OnInit {
  personas: Persona[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.cargarPersonas();
  }

  cargarPersonas(): void {
    this.apiService.getPersonas().subscribe(
      (data) => (this.personas = data.data),
      (error) => console.error('Error al cargar personas', error)
    );
  }

  eliminarPersona(id: number): void {
    this.apiService.eliminarPersona(id).subscribe(
      () => {
        this.cargarPersonas();
      },
      (error) => console.error('Error al eliminar persona', error)
    );
  }
  modificar(id: number): void {
    this.apiService.eliminarPersona(id).subscribe(
      () => {
        this.cargarPersonas();
      },
      (error) => console.error('Error al modificar persona', error)
    );
  }
}
