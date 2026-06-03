import { Routes } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonEdithComponent } from './person-edith/person-edith.component';
import { PersonNuevoComponent } from './person-nuevo/person-nuevo.component';

export const routes: Routes = [

    {
        path: 'person',
        component: PersonListComponent
    },
     {
        path: 'person/nuevo',
        component: PersonNuevoComponent
    },
    {
        path: 'person/:id',
        component: PersonEdithComponent
    },
   
    {
        path: '**',
        redirectTo: '/person',
        pathMatch: 'full'
    }
];
