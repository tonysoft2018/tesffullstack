import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Person } from '../../../core/interfaces/person.interface';
import {MatIconModule} from '@angular/material/icon'; 
import {MatDividerModule} from '@angular/material/divider';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {
  MatDialogRef,
  MatDialogActions,
  MatDialogClose,
  MatDialogTitle,
  MatDialogContent,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { PersonService } from '../../../core/services/person.service';
import { MatError, MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';

@Component({
  selector: 'app-person-edit',
  standalone: true,
  imports: [MatDialogActions,
    MatDialogClose,
    MatDialogTitle,
    MatDialogContent, MatLabel, MatFormField, MatError, 
    MatOption, MatSelect, MatDividerModule, MatIconModule, FormsModule,
    ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatButtonModule],
  templateUrl: './person-edit.component.html',
  styleUrl: './person-edit.component.scss'
})
export class PersonEditComponent implements OnInit {

  todoRegister = {} as Person;
  mode: "create" | "update" = "create";

  form: FormGroup = this.formbuilder.group({
    id: [0],
    nombre: [null, [Validators.required]],
    apellido: [null, [Validators.required]],
    fechaNacimiento: [null, [Validators.required]],
    puesto: [null],
    sueldo: [null],
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) private dataTodo: Person,
    private dialogRef: MatDialogRef<PersonEditComponent>,
    private formbuilder: FormBuilder,
    private personService: PersonService,
  ){
    dialogRef.disableClose = true;
  }

  ngOnInit(): void {

    if (this.dataTodo) {
      console.log("mode update");
      this.mode = "update";
    } else {
      console.log("mode create");
      this.dataTodo = {} as Person;
    }

    this.patchForm();
  }

  operar() {
    this.todoRegister = this.form.value;

    if(this.form.invalid){
      return;
    }

    if (this.mode == "update") {

      this.personService
        .update(this.todoRegister.id, this.todoRegister)
        .subscribe((response) => {
          console.log("response", response);
          if (response.message && response.message == "OK") {
            this.cerrar(response.message);
          }
        });
    }else{
      this.personService
        .create(this.todoRegister)
        .subscribe((response) => {
          if (response.message && response.message == "OK") {
            this.cerrar(response.message);
          }
        });
    }
  }

  patchForm(){
    this.todoRegister = { ...this.dataTodo };

    this.form.patchValue({
      id: this.todoRegister.id,
      nombre: this.todoRegister.nombre,
      apellido: this.todoRegister.apellido,
      fechaNacimiento: this.todoRegister.fechaNacimiento,
      puesto:this.todoRegister.puesto,
      sueldo:this.todoRegister.sueldo
    });
  }

  getName(){
    return (this.todoRegister.nombre+" "+ this.todoRegister.apellido);
  }

  cerrar(status?:string) {
    this.dialogRef.close(status);
  }

  isCreateMode() {
    return this.mode === "create";
  }

  isUpdateMode() {
    return this.mode === "update";
  }

}
