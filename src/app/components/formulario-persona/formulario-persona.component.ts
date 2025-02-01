import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Persona } from '../../models/persona.model';
import {FormsModule} from '@angular/forms';
import {ApiService} from '../../services/api.service';

@Component({
  selector: 'app-formulario-persona',
  templateUrl: './formulario-persona.component.html',
  styleUrls: ['./formulario-persona.component.css'],
  imports: [
    FormsModule
  ],
  standalone: true
})
export class FormularioUsuarioComponent implements OnInit {
  @Input() persona: Persona = { id: 0, nombre: '', apellido: '', sueldo: '',puesto:'',fechaNacimiento:'' };
  @Output() guardaPersona = new EventEmitter<Persona>();

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.persona.id) {
      // Editar usuario
      this.apiService.editarPersona(this.persona).subscribe(
        (data) => {
          this.guardaPersona.emit(data.data);
        },
        (error) => console.error('Error al editar usuario', error)
      );
    } else {
      this.apiService.agregarPersona(this.persona).subscribe(
        (data) => {
          this.guardaPersona.emit(data.data);
        },
        (error) => console.error('Error al agregar usuario', error)
      );
    }
  }
}
