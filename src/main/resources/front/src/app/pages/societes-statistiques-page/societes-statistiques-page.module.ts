import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SocietesStatistiquesPageComponent } from './societes-statistiques-page.component';
import { SharedModule } from '@theme/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AjoutSocieteStatistiqueModalComponent } from './ajout-societe-statistique-modal/ajout-societe-statistique-modal.component';
import { SocietesStatistiquesPageRoutingModule } from './societes-statistiques-page-routing.module';

@NgModule({
  declarations: [SocietesStatistiquesPageComponent, AjoutSocieteStatistiqueModalComponent],
  imports: [
    CommonModule,
    SharedModule,
    SocietesStatistiquesPageRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SocietesStatistiquesPageModule { }
