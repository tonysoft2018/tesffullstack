import { Routes } from '@angular/router';
import { PersonListComponent } from './components/person-list/person-list';
import { PersonFormComponent } from './components/person-form/person-form';

export const routes: Routes = [
  { path: '', component: PersonListComponent },
  { path: 'person/new', component: PersonFormComponent },
  { path: 'person/edit/:id', component: PersonFormComponent }
];
