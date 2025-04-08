import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { IPagination } from '../../../shared/types/pagination.type';
import { IListProduct } from '../models/list-product.model';

@Injectable({
  providedIn: 'root',
})
export class ListProductsService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private http: HttpClient) {}

  getProducts(params: HttpParams) {
    return this.http.get<IPagination<IListProduct>>(this.baseUrl, { params });
  }
}
