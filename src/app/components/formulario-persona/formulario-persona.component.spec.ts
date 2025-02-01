import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Persona } from '../../models/persona.model';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-formulario-usuario',
  templateUrl: './formulario-usuario.component.html',
  styleUrls: ['./formulario-usuario.component.css'],
})
export class FormularioPersonaComponent implements OnInit {
  @Input() usuario: Persona = { id: 0, nombre: '', apellido: '', fechaNacimiento: '',puesto:'',sueldo:'' };
  @Output() guardarPersona = new EventEmitter<Persona>();

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.usuario.id) {
      // Editar usuario
      this.apiService.editarPersona(this.usuario).subscribe(
        (data) => {
          this.guardarPersona.emit(data.data);
        },
        (error) => console.error('Error al editar usuario', error)
      );
    } else {
      // Agregar usuario
      this.apiService.agregarPersona(this.usuario).subscribe(
        (data) => {
          this.guardarPersona.emit(data.data);
        },
        (error) => console.error('Error al agregar usuario', error)
      );
    }
  }
}
