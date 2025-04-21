import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IUpdateProduct } from '../models/update-product.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UpdateProductService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private readonly http: HttpClient) {}

  updateProduct(id: string, body: IUpdateProduct): Observable<void> {
    return this.http.put(`${this.baseUrl}/${id}`, body).pipe(map(() => void 0));
  }
}
