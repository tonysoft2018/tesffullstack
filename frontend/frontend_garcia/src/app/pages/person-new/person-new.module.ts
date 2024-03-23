import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonNewComponent } from './person-new.component';
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
    RouterModule.forChild([{ path: '', component: PersonNewComponent }]),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    DirectivesModule,
    MatSnackBarModule
  ],
  declarations: [PersonNewComponent]
})
export class PersonNewModule { }
