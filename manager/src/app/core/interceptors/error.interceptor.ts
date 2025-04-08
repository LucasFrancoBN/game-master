import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import {
  IException,
  isIException,
} from '../../shared/exception/exception.type';
import { inject } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const message = inject(NzMessageService);

  return next(req).pipe(
    catchError((e: HttpErrorResponse) => {
      if (e.status === 401) {
        message.error(
          'Acesso negado. Faça o login na aplicação para utilizar esse recurso'
        );
        return throwError(() => buildError(e.status, 'Acesso negado.'));
      }

      const error = e.error;

      if (isIException(error)) return throwError(() => error);

      return throwError(() => buildError(e.status));
    })
  );
};

function buildError(status: number, message?: string): IException {
  return {
    timestamp: new Date().toISOString(),
    message: message ? message : 'Erro desconhecido',
    path: '',
    status: status,
  };
}
