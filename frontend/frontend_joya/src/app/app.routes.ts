import { Routes } from '@angular/router';
import { PersonFormComponent } from './pages/person-form/person-form.component';
import { PersonListComponent } from './pages/person-list/person-list.component';

export const routes: Routes = [
  { path: '', component: PersonListComponent },
  { path: 'nuevo', component: PersonFormComponent },
  { path: 'editar/:id', component: PersonFormComponent },
  { path: '**', redirectTo: '' },
];
