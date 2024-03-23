import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../../services/person.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DATE_FORMATS, MAT_DATE_LOCALE, MAT_NATIVE_DATE_FORMATS } from '@angular/material/core';
import moment from 'moment';


@Component({
  selector: 'app-person-edit',
  templateUrl: './person-edit.component.html',
  styleUrls: ['./person-edit.component.css'],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'es-MX' },
    { provide: MAT_DATE_FORMATS, useValue: MAT_NATIVE_DATE_FORMATS }
  ]
})
export class PersonEditComponent implements OnInit {

  public formulario: FormGroup;
  private id: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private personService: PersonService,
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
      this.personService.updatePerson(this.id as string, this.formulario.value).subscribe((response)=>{
        this.snackBar.open(response.msg,"Ok",{ duration: 4000});
        this.router.navigate(["/"]);
      });
    }
  }
  
  ngOnInit() {
    if(this.route.snapshot.data["person"].status){      
      this.id = this.route.snapshot.data["person"].data[0].id;
      delete this.route.snapshot.data["person"].data[0].id;
      this.formulario.setValue(this.route.snapshot.data["person"].data[0]);
      this.formulario.controls["sueldo"].setValue(parseFloat(this.route.snapshot.data["person"].data[0].sueldo).toFixed(2));
      this.formulario.controls["fechaNacimiento"].setValue(moment(this.route.snapshot.data["person"].data[0].fechaNacimiento).toDate());
    }
    else
    this.router.navigate([".."])
    
  }

}
