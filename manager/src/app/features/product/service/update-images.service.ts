import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IProductImage } from '../models/product.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UpdateImagesService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private readonly http: HttpClient) {}

  update(id: string, body: IProductImage[]): Observable<void> {
    return this.http
      .put(`${this.baseUrl}/${id}/image/update-index`, body)
      .pipe(map(() => void 0));
  }
}
