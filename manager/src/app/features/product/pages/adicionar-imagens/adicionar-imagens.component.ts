import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';
import { GetProductByIdService } from '../../service/get-product-by-id.service';
import { IProductImage } from '../../models/product.model';
import { IException } from '../../../../shared/exception/exception.type';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';
import { catchError, finalize, switchMap, throwError } from 'rxjs';
import { NzAlertComponent } from 'ng-zorro-antd/alert';
import { UploadComponent } from '../../../../shared/components/upload/upload.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { AddImagesService } from '../../service/add-images.service';
import { NzMessageService } from 'ng-zorro-antd/message';

const MAX_IMAGES = 5;

@Component({
  selector: 'app-adicionar-imagens',
  imports: [
    LoadingComponent,
    NzAlertComponent,
    UploadComponent,
    ButtonComponent,
  ],
  templateUrl: './adicionar-imagens.component.html',
})
export class AdicionarImagensComponent implements OnInit {
  private readonly message = inject(NzMessageService);
  private readonly route = inject(ActivatedRoute);
  private readonly getProductById = inject(GetProductByIdService);
  private readonly addImage = inject(AddImagesService);
  private readonly destroyRef = inject(DestroyRef);
  private id: string = '';

  productImages?: IProductImage[];
  loading = true;
  error?: string;
  photoList: File[] = [];

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.id = id;
    this.getProductById
      .getProduct(id)
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        finalize(() => (this.loading = false))
      )
      .subscribe({
        next: (data) => {
          this.productImages = data.images;
        },
        error: (e: IException) => (this.error = e.message),
      });
  }

  get invalidPhotos() {
    return (
      !this.photoList ||
      !this.photoList.length ||
      (this.productImages?.length || 0) + this.photoList.length > MAX_IMAGES
    );
  }

  add() {
    this.error = '';

    if (this.invalidPhotos) {
      this.error = 'Quantidade de fotos inválida';
      return;
    }

    this.loading = true;

    this.addImage
      .add(this.id, this.photoList)
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        catchError((addImageError: IException) => {
          this.message.error(addImageError.message);
          return throwError(() => addImageError);
        }),
        switchMap(() => this.getProductById.getProduct(this.id)),
        catchError((getProductError: IException) => {
          this.message.error(getProductError.message);
          return throwError(() => getProductError);
        }),
        finalize(() => {
          this.loading = false;
          this.photoList = [];
        })
      )
      .subscribe((data) => (this.productImages = data.images));
  }
}
