import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from "../../theme/shared/shared.module";
import { ProfilsStatistiquesPageComponent } from './profils-statistiques-page.component';
import { ProfilsStatistiquesPageRoutingModule } from './profils-statistiques-page-routing.module';

@NgModule({
  declarations: [ProfilsStatistiquesPageComponent],
  exports: [ProfilsStatistiquesPageComponent],
  bootstrap: [ProfilsStatistiquesPageComponent],
  imports: [
    CommonModule,
    ProfilsStatistiquesPageRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProfilsStatistiquesPageModule { }
