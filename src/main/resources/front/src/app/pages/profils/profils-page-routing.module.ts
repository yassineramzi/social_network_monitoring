import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfilsPageComponent } from './profils-page.component';
import { AuthGuard } from '@services/auth.guard';
const routes: Routes = [
  {
    path: ':idDossier/dossier',
    component: ProfilsPageComponent,
    canActivate: [AuthGuard], 
    data: { roles: ['ROLE_ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfilsPageRoutingModule { }