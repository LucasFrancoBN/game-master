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
import { translateStatus } from '../../utils/product-status.utils';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { finalize } from 'rxjs';
import { TranslateStatusPipe } from '../../../../shared/pipes/translate-status.pipe';
import { IException } from '../../../../shared/exception/exception.type';

interface IFilterStatus {
  text: string;
  value: ProductStatus;
}

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
  ],
  templateUrl: './listar-produtos.component.html',
})
export class ListarProdutosComponent implements OnInit {
  private readonly DEFAULT_PAGE_SIZE = 10;
  private readonly DEFAULT_PAGE_INDEX = 1;
  private readonly listProductService = inject(ListProductsService);
  private readonly fb = inject(NonNullableFormBuilder);

  error = '';
  loading = false;
  pageSize = this.DEFAULT_PAGE_SIZE;
  pageIndex = this.DEFAULT_PAGE_INDEX;

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
    currentPage: this.DEFAULT_PAGE_INDEX,
    pageSize: this.DEFAULT_PAGE_SIZE,
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
    this.pageIndex = 0;

    this.searchProducts(
      this.pageIndex,
      this.pageSize,
      this.name,
      this.statusFilter
    );
  }

  nameSearch() {
    this.visible = false;
    this.pageIndex = 0;

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
