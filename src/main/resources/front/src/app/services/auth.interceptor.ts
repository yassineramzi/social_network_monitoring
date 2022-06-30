import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { TokenStorageService } from './token-storage.service';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router
    ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.tokenStorageService.getToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          // handle client-side error
        } else {
          switch (error.status) {
            case 401: //login
              {
                this.tokenStorageService.clearSession();
                this.router.navigate(['/login-page']);
              }
              break;
            case 403: //forbidden
              break;
            case 404: //not found
              break;
            case 500: //internal server error
              break;
          }
        }
        return throwError(error);
      })
    );
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
