import { HttpResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild} from '@angular/core';
import StatisticData from '@models/statisticData.model';
import { ProfilStatistiqueService } from '@services/profilStatistique.service';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexDataLabels,
  ApexTooltip,
  ApexStroke
} from "ng-apexcharts";

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  stroke: ApexStroke;
  tooltip: ApexTooltip;
  dataLabels: ApexDataLabels;
};

@Component({
  selector: 'app-dash-board',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashBoardComponent implements OnInit {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptionsYoutubeViews: Partial<ChartOptions>;

  public constructor(protected profilStatistiqueService: ProfilStatistiqueService){
    this.chartOptionsYoutubeViews = {
      series: [
      ],
      chart: {
        height: 350,
        type: "area"
      },
      dataLabels: {
        enabled: true
      },
      stroke: {
        curve: "smooth"
      },
      xaxis: {
        type: "datetime"
      },
      tooltip: {
        x: {
          format: "dd/MM/yy HH:mm"
        }
      }
    };
  }

  public ngOnInit(): void {
    this.profilStatistiqueService.findAllYoutubeViewsStatisticSet().subscribe(
      (response : HttpResponse<StatisticData>) => {
        this.chartOptionsYoutubeViews = {
          series: response.body.statisticSet,
          chart: {
            height: 350,
            type: "area"
          },
          dataLabels: {
            enabled: false
          },
          stroke: {
            curve: "smooth"
          },
          xaxis: {
            type: "datetime",
            categories: response.body.labels
          },
          tooltip: {
            x: {
              format: "dd/MM/yy HH:mm"
            }
          }
        };
      }
    );
  }

}