import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashBoardRoutingModule } from './dashboard-routing.module';
import { DashBoardComponent } from './dashboard.component';
import { SharedModule } from '../../theme/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgApexchartsModule } from "ng-apexcharts";


@NgModule({
  imports: [
    CommonModule,
    DashBoardRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    NgApexchartsModule
  ],
  declarations: [
    DashBoardComponent,
  ],
  providers : [
  ]
})
export class DashBoardModule { }
