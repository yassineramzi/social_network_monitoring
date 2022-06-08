/**
 * data example :
 *      {
          name: 'Cristiano',
          type: 'line',
          data: [440, 505, 414, 671, 227, 413, 201, 352, 752, 320, 257, 160]
        }, {
          name: 'Zidane',
          type: 'line',
          data: [44, 55, 41, 67, 22, 43, 21, 41, 56, 27, 43, 23]
        }, {
          name: 'Rodriguez',
          type: 'line',
          data: [23, 42, 35, 27, 43, 22, 17, 31, 22, 22, 12, 16]
        }
 */

/**
 * labels example :
 * ['01 Jan 2001', '02 Jan 2001', '03 Jan 2001', '04 Jan 2001', '05 Jan 2001', '06 Jan 2001', '07 Jan 2001',
          '08 Jan 2001', '09 Jan 2001', '10 Jan 2001', '11 Jan 2001', '12 Jan 2001']
 */
export default class ChartConfig {
    public youtubeViewsChart: any = {
        chart: {
          height: 300,
          type: 'line',
          animations: {
            enabled: true,
            easing: 'linear',
            dynamicAnimation: {
              speed: 2000
            }
          },
          toolbar: {
            show: false
          },
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },
        series: [],
        colors: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'],
        markers: {
          size: 0
        },
        labels: [],
        xaxis: {
          type: 'datetime'
        },
        yaxis: [{
          title: {
            text: 'Vues Youtube',
          }
        }],
        legend: {
          show: true
        }
    };

    public static youtubeSubscribersChart: any = {
        chart: {
          height: 300,
          type: 'line',
          animations: {
            enabled: true,
            easing: 'linear',
            dynamicAnimation: {
              speed: 2000
            }
          },
          toolbar: {
            show: false
          },
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },
        series: [],
        colors: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'],
        markers: {
          size: 0
        },
        labels: [],
        xaxis: {
          type: 'datetime'
        },
        yaxis: [{
          title: {
            text: 'Subscribers Youtube',
          }
        }],
        legend: {
          show: false
        }
    };;

    public static instagramFollowersChart: any = {
        chart: {
          height: 300,
          type: 'line',
          animations: {
            enabled: true,
            easing: 'linear',
            dynamicAnimation: {
              speed: 2000
            }
          },
          toolbar: {
            show: false
          },
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },
        series: [],
        colors: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'],
        markers: {
          size: 0
        },
        labels: [],
        xaxis: {
          type: 'datetime'
        },
        yaxis: [{
          title: {
            text: 'Followers Instagram',
          }
        }],
        legend: {
          show: false
        }
    };;

    public static twitterFollowersChart: any = {
        chart: {
          height: 300,
          type: 'line',
          animations: {
            enabled: true,
            easing: 'linear',
            dynamicAnimation: {
              speed: 2000
            }
          },
          toolbar: {
            show: false
          },
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth'
        },
        series: [],
        colors: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'],
        markers: {
          size: 0
        },
        labels: [],
        xaxis: {
          type: 'datetime'
        },
        yaxis: [{
          title: {
            text: 'Followers Twitter',
          }
        }],
        legend: {
          show: false
        }
    };;
}