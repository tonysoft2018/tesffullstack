import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonEditComponent } from './person-edit.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DirectivesModule } from '../../directives/directives.module';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: PersonEditComponent }]),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    DirectivesModule,
    MatSnackBarModule
  ],
  declarations: [PersonEditComponent]
})
export class PersonEditModule { }
