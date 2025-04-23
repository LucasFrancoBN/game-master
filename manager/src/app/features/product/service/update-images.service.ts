import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IProductImage } from '../models/product.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UpdateImagesService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/images`;

  constructor(private readonly http: HttpClient) {}

  update(body: IProductImage[]): Observable<void> {
    return this.http.put(this.baseUrl, body).pipe(map(() => void 0));
  }
}
