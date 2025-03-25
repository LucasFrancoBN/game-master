import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import { AuthService } from '../services/auth/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if(req.url.includes('revoke') || req.url.includes('logout')) return next(req);

  const authService = inject(AuthService);

  const accessToken = localStorage.getItem('access_token');
  const refreshToken = localStorage.getItem('refresh_token');
  const expiresIn = localStorage.getItem('expires_in');

  if(!accessToken || !refreshToken || !expiresIn)
    return next(req);


  if(Number(expiresIn) - Date.now() >= 0) {
    authService.handleRefreshToken(refreshToken).pipe();
  }

  const clonedReq = req.clone({
    setHeaders: {Authorization: `Bearer ${accessToken}`}
  })

  return next(clonedReq);
};
