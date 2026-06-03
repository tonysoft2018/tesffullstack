import { Routes } from '@angular/router';

import { PersonListComponent }
from './pages/person-list/person-list';

import { PersonFormComponent }
from './pages/person-form/person-form';

export const routes: Routes = [

  {
    path: '',
    component: PersonListComponent
  },

  {
    path: 'new',
    component: PersonFormComponent
  },

  {
    path: 'edit/:id',
    component: PersonFormComponent
  }
];
