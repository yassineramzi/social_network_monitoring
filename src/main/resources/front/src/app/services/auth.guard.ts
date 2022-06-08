import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';
import { JwtResponse } from '@models/jwtResponse.model';
import { TokenStorageService } from './token-storage.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
    constructor(
      public tokenStorageService: TokenStorageService,
      public router: Router
    ) { }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const currentUser: JwtResponse = this.tokenStorageService.getUser();
      if (currentUser) {
          // check if route is restricted by role
          let rolesAuthorized: Array<string> = route.data.roles;
          if (rolesAuthorized) {
            if (currentUser.roles) {
              for(let roleAuthorized in rolesAuthorized) {
                for(let currentUserRole in currentUser.roles) {
                  if (currentUserRole === roleAuthorized) {
                    return true;
                  }
                }
              }
            }
            this.router.navigate(['/dashboard-page']);
            return false;
          }
          // authorized so return true
          return true;
      }
      // not logged in so redirect to login page with the return url
      this.router.navigate(['/login-page'], { queryParams: { returnUrl: state.url } });
      return false;
    }
}