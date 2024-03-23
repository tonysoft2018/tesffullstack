import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DATE_FORMATS, MAT_DATE_LOCALE, MAT_NATIVE_DATE_FORMATS } from '@angular/material/core';
import { PersonService } from '../../services/person.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-person-new',
  templateUrl: './person-new.component.html',
  styleUrls: ['./person-new.component.css'],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'es-MX' },
    { provide: MAT_DATE_FORMATS, useValue: MAT_NATIVE_DATE_FORMATS }
  ]
})
export class PersonNewComponent implements OnInit {

  public formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private personService: PersonService,
    private router: Router,
    private snackBar: MatSnackBar
  ) { 
    this.formulario = this.formBuilder.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      puesto: ['', Validators.required],
      sueldo: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.formulario.valid) {
      this.personService.createPerson(this.formulario.value).subscribe((response)=>{
        this.snackBar.open(response.msg,"Ok",{ duration: 4000});
        this.router.navigate(["/"]);
      });
    }
  }

  ngOnInit() {
  }

}
