import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IProductModel } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class GetProductByIdService {
  private readonly baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private readonly http: HttpClient) {}

  getProduct(id: string) {
    return this.http.get<IProductModel>(`${this.baseUrl}/${id}`);
  }
}
