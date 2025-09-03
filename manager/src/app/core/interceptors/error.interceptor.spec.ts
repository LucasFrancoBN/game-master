import {
  HttpClient,
  HttpInterceptorFn,
  provideHttpClient,
  withInterceptors,
} from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { NzMessageService } from 'ng-zorro-antd/message';
import {
  IException,
  isIException,
} from '../../shared/exception/exception.type';
import { errorInterceptor } from './error.interceptor';

describe('errorInterceptor', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  let mockMessageService: jest.Mocked<NzMessageService>;
  let mockIsIException: jest.Mock;

  beforeEach(() => {
    mockMessageService = {
      error: jest.fn(),
    } as any;
    mockIsIException = jest.fn();

    TestBed.configureTestingModule({
      providers: [
        { provide: NzMessageService, useValue: mockMessageService },
        provideHttpClient(withInterceptors([errorInterceptor])),
        provideHttpClientTesting(),
      ],
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);

    jest.clearAllMocks();
  });

  it('should be created', () => {
    const interceptor: HttpInterceptorFn = (req, next) =>
      TestBed.runInInjectionContext(() => errorInterceptor(req, next));
    expect(interceptor).toBeTruthy();
  });

  it('should handle 401 error and show access denied message', (done) => {
    const url = 'https://gamemastermanager.com/test';
    const errorMessage =
      'Acesso negado. Faça o login na aplicação para utilizar esse recurso';

    httpClient.get(url).subscribe({
      error: (error: IException) => {
        expect(error.status).toBe(401);
        expect(error.message).toBe('Acesso negado.');
        expect(mockMessageService.error).toHaveBeenCalledWith(errorMessage);
        done();
      },
    });

    const req = httpTestingController.expectOne(url);

    req.flush('Unauthorized', {
      status: 401,
      statusText: 'Unauthorized',
    });
  });

  it('should return IException error when error is IException', (done) => {
    const url = 'https://gamemastermanager.com/test';
    const exception: IException = {
      timestamp: new Date().toISOString(),
      message: 'Custom error message',
      path: '/test',
      status: 400,
    };

    httpClient.get(url).subscribe({
      error: (error: IException) => {
        expect(error).toEqual(exception);
        expect(isIException(error)).toBeTruthy();
        expect(mockMessageService.error).not.toHaveBeenCalled();
        done();
      },
    });

    const req = httpTestingController.expectOne(url);

    req.flush(exception, {
      status: 404,
      statusText: 'Bad Request',
    });
  });

  it('should handle generic error with default message', (done) => {
    const url = 'https://gamemastermanager.com/test';

    httpClient.get(url).subscribe({
      error: (error: IException) => {
        expect(error.status).toBe(500);
        expect(error.message).toBe('Erro desconhecido');
        expect(mockMessageService.error).not.toHaveBeenCalled();
        done();
      },
    });

    const req = httpTestingController.expectOne(url);

    req.flush('Server Error', {
      status: 500,
      statusText: 'Internal Server Error',
    });
  });
});
