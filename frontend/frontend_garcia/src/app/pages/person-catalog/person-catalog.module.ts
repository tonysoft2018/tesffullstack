import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonCatalogComponent } from './person-catalog.component';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: PersonCatalogComponent }]),
    MatTableModule,
    MatButtonModule
  ],
  declarations: [PersonCatalogComponent]
})
export class PersonCatalogModule { }
