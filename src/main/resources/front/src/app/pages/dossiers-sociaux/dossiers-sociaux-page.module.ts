import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../theme/shared/shared.module";
import { DossiersSociauxPageComponent } from './dossiers-sociaux-page.component';
import { DossiersSociauxPageRoutingModule } from './dossiers-sociaux-page-routing.module';

@NgModule({
  declarations: [DossiersSociauxPageComponent],
  exports: [DossiersSociauxPageComponent],
  bootstrap: [DossiersSociauxPageComponent],
  imports: [
    CommonModule,
    DossiersSociauxPageRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class DossiersSociauxPageModule { }
