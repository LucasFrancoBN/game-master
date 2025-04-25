import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AddImagesService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private readonly http: HttpClient) {}

  add(id: string, files: File[]): Observable<void> {
    const formData = new FormData();
    files.forEach((file) => formData.append('images', file));

    return this.http
      .patch(`${this.baseUrl}/${id}`, formData)
      .pipe(map(() => void 0));
  }
}
