import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonCatalogComponent } from './person-catalog.component';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { PersonDeleteComponent } from '../../dialogs/person-delete/person-delete.component';
import { MatDialogActions, MatDialogClose, MatDialogTitle } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: PersonCatalogComponent }]),
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatIconModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogTitle,
    MatSnackBarModule
  ],
  declarations: [PersonCatalogComponent, PersonDeleteComponent]
})
export class PersonCatalogModule { }
