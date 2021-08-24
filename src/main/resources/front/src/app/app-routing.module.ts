import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './theme/layout/admin/admin.component';
import {AuthComponent} from './theme/layout/auth/auth.component';

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: '',
        redirectTo: '/login-page',
        pathMatch: 'full'
      },
      {
        path: 'login-page',
        loadChildren: () => import('@pages/auth-signin/auth-signin.module').then(module => module.AuthSigninModule)
      }
    ]
  },
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        redirectTo: '/dossiers-sociaux-page',
        pathMatch: 'full'
      },
      {
        path: 'dossiers-sociaux-page',
        loadChildren: () => import('@pages/dossiers-sociaux/dossiers-sociaux-page.module').then(module => module.DossiersSociauxPageModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
