import {
  HttpClient,
  HttpInterceptorFn,
  provideHttpClient,
  withInterceptors,
} from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { authInterceptor } from './auth.interceptor';

describe('authInterceptor', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let mockAuthService: jest.Mocked<AuthService>;

  beforeEach(() => {
    mockAuthService = {
      getAccessToken: jest.fn(),
      getRefreshToken: jest.fn(),
      getExpiresIn: jest.fn(),
      handleRefreshToken: jest.fn().mockReturnValue(
        of({
          access_token: 'new_access_token',
          refresh_token: 'new_refresh_token',
          expires_in: Date.now() + 60 * 1000,
        })
      ),
    } as any;

    const localStorageMock = {
      getItem: jest.fn(),
      setItem: jest.fn(),
      removeItem: jest.fn(),
      clear: jest.fn(),
      length: 0,
      key: jest.fn(),
    };

    Object.defineProperty(window, 'localStorage', {
      value: localStorageMock,
      writable: true,
    });

    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: mockAuthService },
        provideHttpClient(withInterceptors([authInterceptor])),
        provideHttpClientTesting(),
      ],
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);

    jest.clearAllMocks();
  });

  it('should be created', () => {
    const interceptor: HttpInterceptorFn = (req, next) =>
      TestBed.runInInjectionContext(() => authInterceptor(req, next));
    expect(interceptor).toBeTruthy();
  });

  it('should add authorization header when valid tokens are present', () => {
    const url = 'https://gamemastermanager.com/test';
    const accessToken = 'test_access_token';
    const refreshToken = 'test_refresh_token';
    const expiresIn = Date.now() + 60 * 1000;

    mockAuthService.getAccessToken.mockReturnValue(accessToken);
    mockAuthService.getRefreshToken.mockReturnValue(refreshToken);
    mockAuthService.getExpiresIn.mockReturnValue(`${expiresIn}`);

    httpClient.get(url).subscribe();

    const req = httpTestingController.expectOne(url);

    expect(req.request.headers.has('Authorization')).toBeTruthy();
    expect(req.request.headers.get('Authorization')).toBe(
      `Bearer ${accessToken}`
    );

    expect(mockAuthService.getAccessToken).toHaveBeenCalledTimes(1);
    expect(mockAuthService.getRefreshToken).toHaveBeenCalledTimes(1);
    expect(mockAuthService.getExpiresIn).toHaveBeenCalledTimes(1);

    expect(mockAuthService.handleRefreshToken).not.toHaveBeenCalled();

    // Finalizar a requisição
    req.flush({ success: true });
  });

  it('should not add authorization header when tokens are missing', () => {
    const url = 'https://gamemastermanager.com/test';

    mockAuthService.getAccessToken.mockReturnValue(null);
    mockAuthService.getRefreshToken.mockReturnValue(null);
    mockAuthService.getExpiresIn.mockReturnValue(null);

    httpClient.get(url).subscribe();

    const req = httpTestingController.expectOne(url);

    expect(req.request.headers.has('Authorization')).toBeFalsy();

    expect(mockAuthService.getAccessToken).toHaveBeenCalledTimes(1);
    expect(mockAuthService.getRefreshToken).toHaveBeenCalledTimes(1);
    expect(mockAuthService.getExpiresIn).toHaveBeenCalledTimes(1);

    expect(mockAuthService.handleRefreshToken).not.toHaveBeenCalled();

    expect(req.request.url).toBe(url);

    // Finalizar a requisição
    req.flush({ success: true });
  });

  it('should handle token refresh when token is expired', () => {
    const url = 'https://gamemastermanager.com/test';
    const oldAccessToken = 'test_old_access_token';
    const newAccessToken = 'test_new_access_token';
    const refreshToken = 'test_valid_refresh_token';
    const expiresIn = Date.now() - 60 * 1000;

    mockAuthService.getAccessToken
      .mockReturnValueOnce(oldAccessToken)
      .mockReturnValueOnce(newAccessToken);

    mockAuthService.getRefreshToken.mockReturnValue(refreshToken);
    mockAuthService.getExpiresIn.mockReturnValue(`${expiresIn}`);

    httpClient.get(url).subscribe();

    const req = httpTestingController.expectOne(url);

    expect(req.request.headers.get('Authorization')).toBe(
      `Bearer ${newAccessToken}`
    );
    expect(mockAuthService.handleRefreshToken).toHaveBeenCalledWith(
      refreshToken
    );
    expect(mockAuthService.handleRefreshToken).toHaveBeenCalledTimes(1);

    expect(mockAuthService.getAccessToken).toHaveBeenCalledTimes(2);
    expect(mockAuthService.getRefreshToken).toHaveBeenCalledTimes(1);
    expect(mockAuthService.getExpiresIn).toHaveBeenCalledTimes(1);

    req.flush({ ok: true });
  });
});
