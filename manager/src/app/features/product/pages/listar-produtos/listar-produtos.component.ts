import { Component, inject, OnInit } from '@angular/core';
import { ListProductsService } from '../../service/list-products.service';
import { HttpParams } from '@angular/common/http';
import { FormsModule, NonNullableFormBuilder } from '@angular/forms';

import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzTableModule, NzTableQueryParams } from 'ng-zorro-antd/table';
import { IListProduct } from '../../models/list-product.model';
import { IPagination } from '../../../../shared/types/pagination.type';
import { ProductStatus } from '../../models/product-status.enum';
import { InputComponent } from '../../../../shared/components/input/input.component';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { catchError, finalize, switchMap, throwError } from 'rxjs';
import { TranslateStatusPipe } from '../../../../shared/pipes/translate-status.pipe';
import { IException } from '../../../../shared/exception/exception.type';
import { RouterLink } from '@angular/router';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { DeleteProductService } from '../../service/delete-product.service';
import { NzMessageService } from 'ng-zorro-antd/message';

interface IFilterStatus {
  text: string;
  value: ProductStatus;
}

const DEFAULT_PAGE_SIZE = 10;
const DEFAULT_PAGE_INDEX = 1;

@Component({
  selector: 'app-listar-produtos',
  imports: [
    FormsModule,
    NzButtonModule,
    NzDropDownModule,
    NzIconModule,
    NzInputModule,
    NzTableModule,
    InputComponent,
    LoadingComponent,
    ButtonComponent,
    TranslateStatusPipe,
    RouterLink,
    NzAlertModule,
    RouterLink,
  ],
  templateUrl: './listar-produtos.component.html',
})
export class ListarProdutosComponent implements OnInit {
  private readonly listProductService = inject(ListProductsService);
  private readonly deleteProductService = inject(DeleteProductService);
  private readonly message = inject(NzMessageService);
  private readonly fb = inject(NonNullableFormBuilder);

  error = '';
  loading = false;
  pageSize = DEFAULT_PAGE_SIZE;
  pageIndex = DEFAULT_PAGE_INDEX;

  visible = false;
  nameFilter = this.fb.control('');
  statusFilter: string[] = [];
  options: IFilterStatus[] = [
    { text: 'Ativo', value: ProductStatus.AVAILABLE },
    { text: 'Descontinuado', value: ProductStatus.DISCOUNTED },
    { text: 'Fora de estoque', value: ProductStatus.OUT_OF_STOCK },
    { text: 'Inativo', value: ProductStatus.UNAVAILABLE },
  ];

  paginationResponse: IPagination<IListProduct> = {
    content: [],
    currentPage: DEFAULT_PAGE_INDEX,
    pageSize: DEFAULT_PAGE_SIZE,
    totalElements: 0,
  };

  ngOnInit(): void {
    this.searchProducts(
      this.pageIndex,
      this.pageSize,
      this.name,
      this.statusFilter
    );
  }

  delete(id: string) {
    this.loading = true;
    this.deleteProductService
      .delete(id)
      .pipe(
        catchError((e: IException) => {
          this.message.error(e.message);

          return throwError(() => e);
        }),
        switchMap(() => {
          const params = this.createParams(
            DEFAULT_PAGE_INDEX,
            DEFAULT_PAGE_SIZE,
            '',
            []
          );
          return this.listProductService.getProducts(params);
        }),
        finalize(() => (this.loading = false))
      )
      .subscribe((data) => (this.paginationResponse = data));
  }

  searchProducts(page: number, size: number, name: string, status: string[]) {
    const params = this.createParams(page, size, name, status);
    this.loading = true;
    this.listProductService
      .getProducts(params)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (data) => {
          this.paginationResponse = data;
        },
        error: (e: IException) => (this.error = e.message),
      });
  }

  onQueryParamsChange({ pageIndex, pageSize, filter }: NzTableQueryParams) {
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
    this.statusFilter = filter[0].value;

    this.searchProducts(
      this.pageIndex,
      this.pageSize,
      this.name,
      this.statusFilter
    );
  }

  reset() {
    this.visible = false;
    this.nameFilter.setValue('');
    this.pageIndex = DEFAULT_PAGE_INDEX;

    this.searchProducts(
      this.pageIndex,
      this.pageSize,
      this.name,
      this.statusFilter
    );
  }

  nameSearch() {
    this.visible = false;
    this.pageIndex = DEFAULT_PAGE_INDEX;

    this.searchProducts(
      this.pageIndex,
      this.pageSize,
      this.name,
      this.statusFilter
    );
  }

  createParams(page: number, size: number, name: string, status: string[]) {
    let params = new HttpParams().append('page', page).append('size', size);

    if (name) params = params.append('name', name);
    if (status.length) params = params.append('status', status.join(';'));

    return params;
  }

  formattedPrice(price: string) {
    return Number(price).toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    });
  }

  public get name(): string {
    return this.nameFilter.getRawValue();
  }
}
