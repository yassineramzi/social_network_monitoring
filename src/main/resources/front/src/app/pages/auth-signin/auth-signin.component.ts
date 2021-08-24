import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

import { TokenStorageService } from '@services/token-storage.service';
import { AuthService } from '@services/auth.service';

import { LoginRequest } from '@models/loginRequest.model';
import { JwtResponse } from '@models/jwtResponse.model';

@Component({
  selector: 'app-auth-signin',
  templateUrl: './auth-signin.component.html',
  styleUrls: ['./auth-signin.component.scss']
})
export class AuthSigninComponent implements OnInit {

  public loginForm: FormGroup = this.formBuilder.group({
    username : new FormControl(null, [Validators.required, Validators.minLength(4)]),
    password : new FormControl(null, [Validators.required, Validators.minLength(8)])
  });
  public isLoggedIn: boolean = false;
  public isLoginFailed: boolean = false;
  public errorMessage: string = '';
  public returnUrl: string ;

  constructor(
    private authService: AuthService,
    private tokenStorageService: TokenStorageService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

 public ngOnInit(): void {
   if (this.tokenStorageService.getToken()) {
     this.isLoggedIn = true;
   }
   this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dossiers-sociaux-page';
 }

 public login(): void {
   let loginRequest: LoginRequest = this.createLoginRequestFromForm();
   this.authService.login(loginRequest).subscribe(
     (response : HttpResponse<JwtResponse>) => {
       this.tokenStorageService.saveToken(response.body.token);
       this.tokenStorageService.saveUser(response.body);
       this.isLoginFailed = false;
       this.isLoggedIn = true;
       this.router.navigate([this.returnUrl]);
     },
     err => {
       this.errorMessage = err.error.message;
       this.isLoginFailed = true;
     }
   );
 }

 private createLoginRequestFromForm(): LoginRequest {
   return new LoginRequest(
     this.loginForm.get(['username'])!.value,
     this.loginForm.get(['password'])!.value
   );
 }

}
