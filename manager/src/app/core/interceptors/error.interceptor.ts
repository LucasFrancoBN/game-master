import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { IException, isIException } from '../../shared/exception/exception.type';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((e: HttpErrorResponse) => {
      const error = e.error;

      if(isIException(error)) return throwError(() => error)

      return throwError(() => {
        const unknowError: IException = {
          timestamp: '',
          message: 'Unknow error',
          path: '',
          status: 0,
        }

        return unknowError
      })
    })
  );
};
