import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SocietesPageComponent } from './societes-page.component';
import { AuthGuard } from '@services/auth.guard';
const routes: Routes = [
  {
    path: '',
    component: SocietesPageComponent,
    canActivate: [AuthGuard], 
    data: { roles: ['ROLE_ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SocietesPageRoutingModule { }