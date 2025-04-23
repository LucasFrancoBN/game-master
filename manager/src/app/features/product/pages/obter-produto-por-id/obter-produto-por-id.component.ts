import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { GetProductByIdService } from '../../service/get-product-by-id.service';
import { IProductModel } from '../../models/product.model';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';
import { finalize } from 'rxjs';
import { TranslateStatusPipe } from '../../../../shared/pipes/translate-status.pipe';
import { ProductStatus } from '../../models/product-status.enum';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { CarouselComponent } from '../../../../shared/components/carousel/carousel.component';
import { IException } from '../../../../shared/exception/exception.type';

@Component({
  selector: 'app-obter-produto-por-id',
  imports: [
    LoadingComponent,
    TranslateStatusPipe,
    NzDividerModule,
    NzIconModule,
    NzTabsModule,
    NzAlertModule,
    CarouselComponent,
  ],
  templateUrl: './obter-produto-por-id.component.html',
})
export class ObterProdutoPorIdComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly getProductById = inject(GetProductByIdService);

  activeImage = false;
  product?: IProductModel;
  loading = false;
  errorMessage?: string;
  statusClass = {
    [ProductStatus.AVAILABLE]:
      'text-sm text-green-800 bg-emerald-100 px-2 py-1 rounded-4xl',
    [ProductStatus.UNAVAILABLE]:
      'text-sm text-orange-900 bg-orange-200 px-2 py-1 rounded-4xl',
    [ProductStatus.OUT_OF_STOCK]:
      'text-sm text-red-900 bg-red-200 px-2 py-1 rounded-4xl',
    [ProductStatus.DISCOUNTED]:
      'text-sm text-amber-900 bg-amber-200 px-2 py-1 rounded-4xl',
  };

  ngOnInit(): void {
    this.loading = true;
    const id = this.route.snapshot.params['id'];
    this.getProductById
      .getProduct(id)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (data) => {
          const orderedImages = data.images.sort((a, b) => a.index - b.index);

          this.product = data;
          this.product.images = orderedImages;
        },
        error: (error: IException) => (this.errorMessage = error.message),
      });
  }

  formattedPrice(price: number) {
    return price.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    });
  }
}
