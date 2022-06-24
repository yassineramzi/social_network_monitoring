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
        redirectTo: '/dashboard-page',
        pathMatch: 'full'
      },
      {
        path: 'dossiers-sociaux-page',
        loadChildren: () => import('@pages/dossiers-sociaux/dossiers-sociaux-page.module').then(module => module.DossiersSociauxPageModule)
      },
      {
        path: 'profils-page',
        loadChildren: () => import('@pages/profils/profils-page.module').then(module => module.ProfilsPageModule)
      },
      {
        path: 'profils-statistiques-page',
        loadChildren: () => import('@pages/profils-statistiques/profils-statistiques-page.module').then(module => module.ProfilsStatistiquesPageModule)
      },
      {
        path: 'dashboard-page',
        loadChildren: () => import('@pages/dashboard/dashboard.module').then(module => module.DashBoardModule)
      },
      {
        path: 'societes-page',
        loadChildren: () => import('@pages/societes-page/societes-page.module').then(module => module.SocietesPageModule)
      },
      {
        path: 'societes-statistiques-page',
        loadChildren: () => import('@pages/societes-statistiques-page/societes-statistiques-page.module').then(module => module.SocietesStatistiquesPageModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
