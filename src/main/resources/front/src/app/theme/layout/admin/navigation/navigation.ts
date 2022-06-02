import {Injectable} from '@angular/core';

export interface NavigationItem {
  id: string;
  title: string;
  type: 'item' | 'collapse' | 'group';
  translate?: string;
  icon?: string;
  hidden?: boolean;
  url?: string;
  classes?: string;
  exactMatch?: boolean;
  external?: boolean;
  target?: boolean;
  breadcrumbs?: boolean;
  function?: any;
  badge?: {
    title?: string;
    type?: string;
  };
  children?: Navigation[];
}

export interface Navigation extends NavigationItem {
  children?: NavigationItem[];
}

const NavigationItems = [
  {
    id: 'navigation',
    title: 'Navigation',
    type: 'group',
    icon: 'feather icon-align-left',
    children: [
      {
        id: 'dashboard',
        title: 'Dashboard',
        type: 'item',
        url: '/dashboard-page',
        classes: 'nav-item',
        icon: 'feather icon-home',
      },
      {
        id: 'dossiers-sociaux-page',
        title: 'Dossiers Sociaux',
        type: 'item',
        url: '/dossiers-sociaux-page',
        classes: 'nav-item',
        icon: 'feather icon-folder',
      },
      {
        id: 'profils-page',
        title: 'Profils',
        type: 'item',
        url: '/profils-page',
        classes: 'nav-item',
        icon: 'feather icon-user',
      }
    ]
  }
];

@Injectable()
export class NavigationItem {
  public get() {
    return NavigationItems;
  }
}
