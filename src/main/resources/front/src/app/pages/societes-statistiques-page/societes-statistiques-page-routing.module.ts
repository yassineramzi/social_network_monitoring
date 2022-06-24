import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '@services/auth.guard';
import { SocietesStatistiquesPageComponent } from './societes-statistiques-page.component';
const routes: Routes = [
  {
    path: '',
    component: SocietesStatistiquesPageComponent,
    canActivate: [AuthGuard], 
    data: { roles: ['ROLE_ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SocietesStatistiquesPageRoutingModule { }