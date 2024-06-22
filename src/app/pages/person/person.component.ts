import { Component, OnInit } from '@angular/core';
import { MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

import { PersonService } from '../../core/services/person.service';
import { Person, Response } from '../../core/interfaces/person.interface';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogTitle,
  MatDialogContent
 } from '@angular/material/dialog';
import { PersonEditComponent } from './person-edit/person-edit.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatFormFieldModule, MatSelectModule,
    MatDialogActions,
  MatDialogClose,
  MatDialogTitle,
  MatDialogContent, MatIconModule, MatButtonModule,ReactiveFormsModule
  ],
  providers: [PersonService],
  templateUrl: './person.component.html',
  styleUrl: './person.component.scss'
})
export class TodoComponent implements OnInit{
  displayedColumns: string[] = ['nombre', 'apellido', 'fechaNacimiento', 'puesto', 'sueldo', 'acciones'];

  pageSizeOptions: number[] = [5, 10, 20, 50];
  dataSource: Person[] = [];
  response: Response<Person[]> | undefined;

  form: FormGroup = this.formbuilder.group({
    priority: [null],
    status: [null]
  });

  constructor(
    private personService: PersonService,
    private dialog: MatDialog,
    private formbuilder: FormBuilder,
  ){
  }

  ngOnInit(){
    this.getAll();
  }

  openDialog(person?: Person) {
    this.dialog.open(PersonEditComponent, {
      data: person,
      width: '30vw'
    }).afterClosed().subscribe(response => {
      this.getAll();
    })
    
  }

  deleteTodo(todo:Person){
    this.personService.delete(todo.id).subscribe({
      next: (response) => {
        if(response.message == "OK"){
          this.getAll();
        }
      }
    }
    )
  }

  getAll() {
    this.personService.getAll().subscribe({
      next: (response) => {
        this.response = response;
        this.dataSource = this.response.data
      },
    });
  }

}
