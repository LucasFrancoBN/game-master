import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GetProductByIdService } from '../../service/get-product-by-id.service';
import { IException } from '../../../../shared/exception/exception.type';
import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { InputComponent } from '../../../../shared/components/input/input.component';
import { SelectComponent } from '../../../../shared/components/select/select.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';
import { finalize } from 'rxjs';
import {
  statusTranslations,
  statusTranslationsReverse,
} from '../../utils/product-status.utils';
import { UpdateProductService } from '../../service/update-product.service';
import { IUpdateProduct } from '../../models/update-product.model';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-editar-produto',
  imports: [
    ReactiveFormsModule,
    NzFormModule,
    NzAlertModule,
    InputComponent,
    SelectComponent,
    ButtonComponent,
    LoadingComponent,
  ],
  templateUrl: './editar-produto.component.html',
})
export class EditarProdutoComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly getProductById = inject(GetProductByIdService);
  private readonly updateProduct = inject(UpdateProductService);
  private readonly fb = inject(NonNullableFormBuilder);
  private readonly message = inject(NzMessageService);

  id: string = '';
  options = Object.values(statusTranslations);
  loading = true;
  productForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(5)]],
    description: ['', [Validators.required, Validators.minLength(100)]],
    amount: [0, [Validators.required, Validators.min(0)]],
    price: [0.0, [Validators.required, Validators.min(1)]],
    weight: [0.0, [Validators.required, Validators.min(1)]],
    status: ['', Validators.required],
  });
  error?: string;

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.id = id;
    this.getProductById
      .getProduct(id)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (data) => {
          this.productForm.setValue({
            name: data.name,
            description: data.description,
            amount: data.amount,
            price: data.price,
            weight: data.weight,
            status: statusTranslations[data.status],
          });
        },
        error: (e: IException) => (this.error = e.message),
      });
  }

  getError(field: string) {
    const control = this.productForm.get(field);

    if (control?.hasError('required')) return 'Esse campo é obrigatório';

    if (control?.hasError('minlength'))
      return `Mínimo de ${control.errors?.['minlength'].requiredLength} caracteres.`;

    if (control?.hasError('min'))
      return `Valor mínimo de ${control.errors?.['min'].min} não atingido`;

    return '';
  }

  get invalidForm() {
    return this.productForm.invalid;
  }

  onSubmit() {
    this.error = '';

    if (this.invalidForm) {
      this.productForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    const body = this.productForm.getRawValue() as IUpdateProduct;
    body.status = statusTranslationsReverse[body.status];
    this.updateProduct
      .updateProduct(this.id, body)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: () => this.message.success('Produto atualizado com sucesso'),
        error: (e: IException) => (this.error = e.message),
      });
  }
}
