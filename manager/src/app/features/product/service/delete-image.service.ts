import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DeleteImageService {
  private readonly baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/images`;

  constructor(private readonly http: HttpClient) {}

  delete(name: string): Observable<void> {
    return this.http.delete(`${this.baseUrl}/${name}`).pipe(map(() => void 0));
  }
}
