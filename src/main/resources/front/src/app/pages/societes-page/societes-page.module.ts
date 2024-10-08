import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SocietesPageComponent } from './societes-page.component';
import { SocietesPageRoutingModule } from './societes-page-routing.module';
import { SharedModule } from '@theme/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AjoutSocietesModalComponent } from './ajout-societes-modal/ajout-societes-modal.component';

@NgModule({
  declarations: [SocietesPageComponent, AjoutSocietesModalComponent],
  imports: [
    CommonModule,
    SocietesPageRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SocietesPageModule { }
