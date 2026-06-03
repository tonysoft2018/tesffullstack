import { CommonModule, Location } from '@angular/common';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { PersonService } from '../services/person.service';
import { PersonResponse } from '../model/person-response';

@Component({
  selector: 'app-person-edith',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [PersonService],
  templateUrl: './person-edith.component.html',
  styleUrl: './person-edith.component.css'
})
export class PersonEdithComponent implements OnInit {


  idPerson: number = 0;
  personResponse: PersonResponse | null = null;

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
    private _activatedRouter: ActivatedRoute,
  ) {

  }

  ngOnInit(): void {
    if (!this._activatedRouter.snapshot.paramMap.has('id')) {

      this._location.back();
      return;
    }

    this.idPerson = Number(this._activatedRouter.snapshot.paramMap.get('id'));
    this.personService.getPersonById(this.idPerson).subscribe({
      next: data => {
        if (data.status) {
          console.log(data);

          this.updatePersonRequestForm = new FormGroup({
            nombre: new FormControl(data.data.nombre, [Validators.required, Validators.min(2)]),
            apellido: new FormControl(data.data.apellido, [Validators.required, Validators.min(2)]),
            fechaNacimiento: new FormControl(new Date(data.data.fechaNacimiento).toISOString().split('T')[0], [Validators.required]),
            puesto: new FormControl(data.data.puesto, [Validators.required, Validators.min(4)]),
            sueldo: new FormControl(data.data.sueldo, [Validators.required, Validators.min(1)]),
          });

        } else {
          this.getBack();
        }
      },
      error: err => {
        this.getBack();
      }
    });
  }

  public update() {
    let values = this.updatePersonRequestForm.value;
    this.personService.updatePerson({
      id: this.idPerson!,
      nombre: values.nombre!,
      apellido: values.apellido!,
      fechaNacimiento: values.fechaNacimiento!,
      sueldo: values.sueldo!,
      puesto: values.puesto!,

    }).subscribe({
      next: data => {
        console.log(data);


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
