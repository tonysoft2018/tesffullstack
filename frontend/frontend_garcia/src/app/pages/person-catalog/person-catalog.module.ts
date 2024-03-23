import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonCatalogComponent } from './person-catalog.component';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: PersonCatalogComponent }]),
    MatTableModule
  ],
  declarations: [PersonCatalogComponent]
})
export class PersonCatalogModule { }
