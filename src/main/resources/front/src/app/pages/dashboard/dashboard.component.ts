import { HttpResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild} from '@angular/core';
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
import StatisticSet from '@models/statisticSet.model';
import { SocieteStatistiquesService } from '@services/societeStatistique.service';

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
  
  @ViewChild("chart-prix-gazoil") chartPrixGazoil: ChartComponent;
  public chartOptionsPrixGazoil: Partial<ChartOptions>;

  @ViewChild("chart-prix-essence") chartPrixEssence: ChartComponent;
  public chartOptionsPrixEssence: Partial<ChartOptions>;

  public constructor(
    protected profilStatistiqueService: ProfilStatistiqueService,
    protected societeStatistiqueService: SocieteStatistiquesService
    ){
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

    this.chartOptionsPrixGazoil = {
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

    this.chartOptionsPrixEssence = {
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
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsYoutubeViews = {
          series: response.body,
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
      },
      
    );
    // Instagram Followers
    this.profilStatistiqueService.findAllInstagramFollowersStatisticSet().subscribe(
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsInstagramFollowers = {
          series: response.body,
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
    );
    // Youtube Subscribers
    this.profilStatistiqueService.findAllYoutubeSubscribersStatisticSet().subscribe(
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsYoutubeSubscribers = {
          series: response.body,
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
    );
    // Twitter Followers
    this.profilStatistiqueService.findAllTwitterFollowersStatisticSet().subscribe(
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsTwitterFollowers = {
          series: response.body,
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
    );
    // Statistiques Gazoil
    this.societeStatistiqueService.findAllGazoilStatisticSet().subscribe(
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsPrixGazoil = {
          series: response.body,
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
    );
    // Prix Essence
    this.societeStatistiqueService.findAllEssenceStatisticSet().subscribe(
      (response : HttpResponse<Array<StatisticSet>>) => {
        this.chartOptionsPrixEssence = {
          series: response.body,
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
    )
  }

}