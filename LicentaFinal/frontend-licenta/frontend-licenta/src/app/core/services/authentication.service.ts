import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {StorageKeys} from "../../globals/storage-keys";
import {AccessToken, AuthData} from "../../shared/types/authentication.types";
import {PATHS} from "../../globals/routes";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient, private router: Router) {}

  isLoggedIn$ = new BehaviorSubject(false);

  userId?: string;

  private errorStatusSubject = new BehaviorSubject<number>(0);

  getUserId(): string | undefined |null {
    return  localStorage.getItem(StorageKeys.USER_ID);
  }
  setErrorStatus(status: number): void {
    this.errorStatusSubject.next(status);
  }

  getErrorStatus(): Observable<number> {
    return this.errorStatusSubject.asObservable();
  }

  login(authData: AuthData): Observable<AccessToken> {
    return this.http
      .post<AccessToken>(environment.apiUrl + '/auth/login', authData)
      .pipe(
        tap((res: AccessToken) => {
          localStorage.setItem(StorageKeys.TOKEN, res.accessToken);
          this.isLoggedIn$.next(true);
          this.getUserDetails();
        })
      );
  }

  getUserDetails(): void {
    const token = localStorage.getItem(StorageKeys.TOKEN);
    if (token != null) {
      this.isLoggedIn$.next(true);
      const jwtData = token.split('.')[1];
      const decodedJwtJsonData = window.atob(jwtData);
      const decodedJwtData = JSON.parse(decodedJwtJsonData);
      const expirationTime = decodedJwtData.exp * 1000;
      const currentTime = new Date().getTime();
      const expirationDuration = expirationTime - currentTime;
      this.autoLogout(expirationDuration);

      this.router.navigate([
        PATHS.HOME
      ]);
      this.userId = decodedJwtData.userId;
      // @ts-ignore
      localStorage.setItem(StorageKeys.USER_ID,this.userId);

    } else {
      localStorage.removeItem(StorageKeys.TOKEN);
      this.userId = undefined;
      this.isLoggedIn$.next(false);
    }
  }

  logout(): void {
    localStorage.removeItem(StorageKeys.TOKEN);
    localStorage.removeItem(StorageKeys.USER_ID);
    this.userId = undefined;
    this.isLoggedIn$.next(false);
    this.router.navigate([PATHS.LOGIN]);
  }

  private autoLogout(expirationDuration: number): void {
    setTimeout(() => {
      this.logout();
    }, expirationDuration);
  }
}
