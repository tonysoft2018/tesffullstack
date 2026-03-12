import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './person-form.html',
  styleUrls: ['./person-form.css']
})
export class PersonFormComponent implements OnInit {
  idToEdit: number | null = null;
  isEdit = false;

  person: any = {
    id: null,
    nombre: '',
    apellido: '',
    fechaNacimiento: '',
    puesto: '',
    sueldo: 0
  };

  constructor(
    private personService: PersonService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.idToEdit = Number(id);
      this.personService.getPersonById(this.idToEdit).subscribe({
        next: (res) => {
          if (res.status && res.data) {
            this.person = res.data;
              if (this.person.fechaNacimiento) {
                this.person.fechaNacimiento = this.person.fechaNacimiento.split('T')[0];
              }
              this.cdr.detectChanges()
          }
        },
        error: (err) => console.error('Error al cargar persona', err)
      });
    }
  }

  onSave() {
    const action = this.isEdit && this.idToEdit !== null
      ? this.personService.updatePerson(this.idToEdit, this.person)
      : this.personService.createPerson(this.person);

    action.subscribe({
      next: (res) => {
        alert(res.msg || "Operación realizada con éxito");
        this.router.navigate(['/']).then(() => {
          window.location.reload();
        });
      },
      error: (err) => {
        console.error('Error en la operación', err);
        alert("Hubo un error al procesar la solicitud.");
      }
    });
  }

  cancel() {
    this.router.navigate(['/']);
  }
}