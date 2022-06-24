import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SocietesStatistiquesPageComponent } from './societes-statistiques-page.component';
import { SharedModule } from '@theme/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [SocietesStatistiquesPageComponent],
  imports: [
    CommonModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SocietesStatistiquesPageModule { }
