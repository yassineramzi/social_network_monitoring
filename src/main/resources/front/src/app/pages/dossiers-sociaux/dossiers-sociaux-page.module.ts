import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../theme/shared/shared.module";
import { DossiersSociauxPageComponent } from './dossiers-sociaux-page.component';
import { DossiersSociauxPageRoutingModule } from './dossiers-sociaux-page-routing.module';
import { AjoutDossierModalComponent } from '@pages/dossiers-sociaux/ajout-dossier-modal/ajout-dossier-modal.component';

@NgModule({
  declarations: [DossiersSociauxPageComponent, AjoutDossierModalComponent],
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
