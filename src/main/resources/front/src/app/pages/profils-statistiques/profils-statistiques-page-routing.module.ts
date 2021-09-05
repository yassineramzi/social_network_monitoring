import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfilsStatistiquesPageComponent } from './profils-statistiques-page.component';
import { AuthGuard } from '@services/auth.guard';
const routes: Routes = [
  {
    path: ':idProfil/profil',
    component: ProfilsStatistiquesPageComponent,
    canActivate: [AuthGuard], 
    data: { roles: ['ROLE_ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfilsStatistiquesPageRoutingModule { }