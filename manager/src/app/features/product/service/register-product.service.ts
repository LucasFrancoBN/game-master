import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { IRegisterProduct } from '../models/register-product.model';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterProductService {
  private baseUrl = `${environment.hostUrl}/gamemastermanager/api/v1/products`;

  constructor(private http: HttpClient) { }

  registerProduct(body: IRegisterProduct, photoList: File[]): Observable<void> {
    const formData = new FormData();

    const jsonBlob = new Blob([JSON.stringify(body)], {type: 'application/json'});

    formData.append("product_data", jsonBlob);

    photoList.forEach(photo => formData.append('images', photo))

    return this.http.post(this.baseUrl, formData).pipe(map(() => void 0));
  }
}
