import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DeleteImageService {
  private readonly baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private readonly http: HttpClient) {}

  delete(id: string, name: string): Observable<void> {
    return this.http
      .delete(`${this.baseUrl}/${id}/image/${name}`)
      .pipe(map(() => void 0));
  }
}
