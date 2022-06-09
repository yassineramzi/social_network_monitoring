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
  ApexStroke,
  ApexResponsive
} from "ng-apexcharts";

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  stroke: ApexStroke;
  tooltip: ApexTooltip;
  dataLabels: ApexDataLabels;
  responsive: Array<ApexResponsive>;
};

@Component({
  selector: 'app-dash-board',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashBoardComponent implements OnInit {
  @ViewChild("chart-youtube-views") chartYoutubeViews: ChartComponent;
  public chartOptionsYoutubeViews: Partial<ChartOptions>;

  @ViewChild("chart-youtube-subscribers") chartYoutubeSubscribers: ChartComponent;
  public chartOptionsYoutubeSubscribers: Partial<ChartOptions>;

  @ViewChild("chart-twitter-followers") chartTwitterFollowers: ChartComponent;
  public chartOptionsTwitterFollowers: Partial<ChartOptions>;

  @ViewChild("chart-instagram-followers") chartInstagramFollowers: ChartComponent;
  public chartOptionsInstagramFollowers: Partial<ChartOptions>;

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
      },
      responsive: [
        {
          breakpoint: 1000,
          options: {
            plotOptions: {
              bar: {
                horizontal: false
              }
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };

    this.chartOptionsYoutubeSubscribers = {
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
      },
      responsive: [
        {
          breakpoint: 1000,
          options: {
            plotOptions: {
              bar: {
                horizontal: false
              }
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };

    this.chartOptionsTwitterFollowers = {
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
      },
      responsive: [
        {
          breakpoint: 1000,
          options: {
            plotOptions: {
              bar: {
                horizontal: false
              }
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };

    this.chartOptionsInstagramFollowers = {
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
      },
      responsive: [
        {
          breakpoint: 1000,
          options: {
            plotOptions: {
              bar: {
                horizontal: false
              }
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
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
          },
          responsive: [
            {
              breakpoint: 1000,
              options: {
                plotOptions: {
                  bar: {
                    horizontal: false
                  }
                },
                legend: {
                  position: "bottom"
                }
              }
            }
          ]
        };
      },
      
    );
    // Instagram Followers
    this.profilStatistiqueService.findAllInstagramFollowersStatisticSet().subscribe(
      (response : HttpResponse<StatisticData>) => {
        this.chartOptionsInstagramFollowers = {
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
          },
          responsive: [
            {
              breakpoint: 1000,
              options: {
                plotOptions: {
                  bar: {
                    horizontal: false
                  }
                },
                legend: {
                  position: "bottom"
                }
              }
            }
          ]
        };
      }
    );
    // Youtube Subscribers
    this.profilStatistiqueService.findAllYoutubeSubscribersStatisticSet().subscribe(
      (response : HttpResponse<StatisticData>) => {
        this.chartOptionsYoutubeSubscribers = {
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
          },
          responsive: [
            {
              breakpoint: 1000,
              options: {
                plotOptions: {
                  bar: {
                    horizontal: false
                  }
                },
                legend: {
                  position: "bottom"
                }
              }
            }
          ]
        };
      }
    );
    // Twitter Followers
    this.profilStatistiqueService.findAllTwitterFollowersStatisticSet().subscribe(
      (response : HttpResponse<StatisticData>) => {
        this.chartOptionsTwitterFollowers = {
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
          },
          responsive: [
            {
              breakpoint: 1000,
              options: {
                plotOptions: {
                  bar: {
                    horizontal: false
                  }
                },
                legend: {
                  position: "bottom"
                }
              }
            }
          ]
        };
      }
    );
  }

}