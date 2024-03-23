import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PersonResolver } from './resolvers/person.resolver';

export const routes: Routes = [
  {
      path: "",
      component: AppComponent,
      children:[
            {
              path: "",
              loadChildren: () => import('./pages/person-catalog/person-catalog.module').then(m => m.PersonCatalogModule)
            },
            {
              path: "new",
              loadChildren: () => import('./pages/person-new/person-new.module').then(m => m.PersonNewModule)
            },{
              path: ":id",
              loadChildren: () => import('./pages/person-edit/person-edit.module').then(m => m.PersonEditModule),
              resolve:{ person: PersonResolver }
            },
      ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
