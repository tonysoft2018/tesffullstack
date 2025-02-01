import { Component } from '@angular/core';
import { Persona } from './models/persona.model';
import { ApiService } from './services/api.service';
import {ListaPersonasComponent} from './components/lista-personas/lista-personas.component';
import {FormularioUsuarioComponent} from './components/formulario-persona/formulario-persona.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [
    ListaPersonasComponent,
    FormularioUsuarioComponent
  ],
  standalone: true
})
export class AppComponent {
  constructor(private apiService: ApiService) {}

  onGuardarPersona(persona: Persona): void {
    if (persona.id) {
      this.apiService.editarPersona(persona).subscribe(
        () => {
          console.log('Persona actualizado');
        },
        (error) => console.error('Error al actualizar persona', error)
      );
    } else {
      this.apiService.agregarPersona(persona).subscribe(
        () => {
          console.log('Persona agregado');
        },
        (error) => console.error('Error al agregar persona', error)
      );
    }
  }
}
