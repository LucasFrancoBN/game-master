import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable, shareReplay, tap} from 'rxjs';
import {IAccessTokenModel} from '../models/access-token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly AUTH_STORAGE_KEYS = {
    accessToken: 'access_token',
    refreshToken: 'refresh_token',
    expiresIn: 'expires_in'
  };
  private readonly basicAuthHeader = `Basic ${btoa(`${environment.client_id}:${environment.client_secret}`)}`;

  constructor(private http: HttpClient, private router: Router) { }

  handleAccessToken(code: string): Observable<any> {
    return this.requestToken(
      this.getTokenRequestBody(environment.grant_type.code, {code, redirect_uri: environment.redirect_uri})
    )
  }

  handleRefreshToken(refreshToken: string): Observable<IAccessTokenModel> {
    return this.requestToken(
      this.getTokenRequestBody(environment.grant_type.code, {refresh_token: refreshToken})
    );
  }

  private requestToken(body: HttpParams): Observable<IAccessTokenModel> {
    return this.http.post<IAccessTokenModel>(
      `${environment.authUrl}/oauth2/token`,
      body,
      {
        headers: {
          "Authorization": this.basicAuthHeader,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
    ).pipe(
      tap(token => this.storeToken(token)),
    );
  }

  private getTokenRequestBody(grantType: string, params: Record<string, string>) {
    let body = new HttpParams().set("grant_type", grantType);
    Object.entries(params).forEach(([key, value]) => {
      body = body.set(key, value);
    })

    return body;
  }

  private storeToken(token: IAccessTokenModel) {
    localStorage.setItem(this.AUTH_STORAGE_KEYS.accessToken, token.access_token);
    localStorage.setItem(this.AUTH_STORAGE_KEYS.refreshToken, token.refresh_token);
    localStorage.setItem(this.AUTH_STORAGE_KEYS.expiresIn, `${Date.now() + token.expires_in * 1000}`);
  }

  logout() {
    localStorage.removeItem(this.AUTH_STORAGE_KEYS.accessToken);
    localStorage.removeItem(this.AUTH_STORAGE_KEYS.refreshToken);
    localStorage.removeItem(this.AUTH_STORAGE_KEYS.expiresIn);

    this.router.navigate(['/']);
  }
}
