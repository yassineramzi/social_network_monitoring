import { HttpResponse } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import StatisticData from '@models/statisticData.model';
import { ProfilStatistiqueService } from '@services/profilStatistique.service';
import ChartConfig from './config/chart-config';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashBoardComponent implements OnInit {

  public youtubeViewsConfig: any;
  public youtubeSubscribersConfig: any;
  public instagramFollowersConfig: any;
  public twitterFollowersConfig: any;

  public constructor(protected profilStatistiqueService: ProfilStatistiqueService){
  }

  public ngOnInit(): void {
    this.youtubeViewsConfig = ChartConfig.youtubeViewsChart;
    this.profilStatistiqueService.findAllYoutubeViewsStatisticSet().subscribe(
      (response : HttpResponse<StatisticData>) => {
        const statisticData: StatisticData = response.body;
        this.youtubeViewsConfig.series = statisticData.statisticSet;
        this.youtubeViewsConfig.labels = statisticData.labels;
      }
    );
    this.youtubeSubscribersConfig = ChartConfig.youtubeSubscribersChart;
    this.instagramFollowersConfig = ChartConfig.instagramFollowersChart;
    this.twitterFollowersConfig = ChartConfig.twitterFollowersChart;
  }

}
