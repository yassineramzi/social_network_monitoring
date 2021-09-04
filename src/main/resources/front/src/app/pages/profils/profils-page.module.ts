import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from "../../theme/shared/shared.module";
import { ProfilsPageComponent } from './profils-page.component';
import { ProfilsPageRoutingModule } from './profils-page-routing.module';
import { AjoutProfilModalComponent } from '@pages/profils/ajout-profil-modal/ajout-profil-modal.component';

@NgModule({
  declarations: [ProfilsPageComponent, AjoutProfilModalComponent],
  exports: [ProfilsPageComponent],
  bootstrap: [ProfilsPageComponent],
  imports: [
    CommonModule,
    ProfilsPageRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProfilsPageModule { }
