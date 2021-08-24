import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DossiersSociauxPageComponent } from './dossiers-sociaux-page.component';
import { AuthGuard } from '@services/auth.guard';
const routes: Routes = [
  {
    path: '',
    component: DossiersSociauxPageComponent,
    canActivate: [AuthGuard], 
    data: { roles: ['ROLE_ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DossiersSociauxPageRoutingModule { }