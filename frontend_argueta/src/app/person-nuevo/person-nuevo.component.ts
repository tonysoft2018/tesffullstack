import { Component, OnInit } from '@angular/core';
import { PersonResponse } from '../model/person-response';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PersonService } from '../services/person.service';
import { CommonModule, Location } from '@angular/common';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-person-nuevo',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [PersonService],
  templateUrl: './person-nuevo.component.html',
  styleUrl: './person-nuevo.component.css'
})
export class PersonNuevoComponent implements OnInit {

  private readonly updatePersonFormValidation = {
    nombre: new FormControl('', [Validators.required, Validators.min(2)]),
    apellido: new FormControl('', [Validators.required, Validators.min(2)]),
    fechaNacimiento: new FormControl(new Date().toISOString().split('T')[0], [Validators.required]),
    puesto: new FormControl('', [Validators.required, Validators.min(4)]),
    sueldo: new FormControl(0, [Validators.required, Validators.min(1)]),
  };
  public updatePersonRequestForm = new FormGroup(this.updatePersonFormValidation);

  constructor(
    private personService: PersonService,
    private _location: Location,
  ) { }

  ngOnInit(): void {
    this.updatePersonRequestForm = new FormGroup(this.updatePersonFormValidation);
  }

  public create() {
    let values = this.updatePersonRequestForm.value;
    this.personService.createPerson({
      nombre: values.nombre!,
      apellido: values.apellido!,
      fechaNacimiento: values.fechaNacimiento!,
      sueldo: values.sueldo!,
      puesto: values.puesto!,

    }).subscribe({
      next: data => {
        this.getBack();
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        this.getBack();
      }
    });

  }
  public getBack() {
    this._location.back();
  }
}
