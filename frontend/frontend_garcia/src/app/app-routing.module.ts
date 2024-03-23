import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

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
