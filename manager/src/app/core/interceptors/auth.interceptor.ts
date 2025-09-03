import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, switchMap } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if (
    req.url.includes('revoke') ||
    req.url.includes('logout') ||
    req.url.includes('token')
  )
    return next(req);

  const authService = inject(AuthService);

  let accessToken = authService.getAccessToken();
  let refreshToken = authService.getRefreshToken();
  let expiresIn = authService.getExpiresIn();

  if (!accessToken || !refreshToken || !expiresIn) return next(req);

  const isTokenExpired = Date.now() >= Number(expiresIn);

  if (!isTokenExpired) {
    const clonedReq = req.clone({
      setHeaders: { Authorization: `Bearer ${accessToken}` },
    });

    return next(clonedReq);
  }

  return authService.handleRefreshToken(refreshToken).pipe(
    switchMap(() => {
      const newAccessToken = authService.getAccessToken();

      if (!newAccessToken) return next(req);

      const clonedReq = req.clone({
        setHeaders: { Authorization: `Bearer ${newAccessToken}` },
      });

      return next(clonedReq);
    }),
    catchError(() => {
      return next(req);
    })
  );
};
