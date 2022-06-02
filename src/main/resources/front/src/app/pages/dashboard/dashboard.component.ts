import { Component, OnInit} from '@angular/core';
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

  public ngOnInit(): void {
    this.youtubeViewsConfig = ChartConfig.youtubeViewsChart;
    this.youtubeSubscribersConfig = ChartConfig.youtubeSubscribersChart;
    this.instagramFollowersConfig = ChartConfig.instagramFollowersChart;
    this.twitterFollowersConfig = ChartConfig.twitterFollowersChart;
  }

}
