import { Routes } from '@angular/router';
import { SkeletonComponent } from './layout/skeleton/skeleton.component';

export const routes: Routes = [
      { 
        path : '', 
        component: SkeletonComponent,
        children: [
          {
          path          : '',
          loadComponent : () => import('./pages/person/person.component').then(m => m.TodoComponent),
          } 
        ]
      }
];
