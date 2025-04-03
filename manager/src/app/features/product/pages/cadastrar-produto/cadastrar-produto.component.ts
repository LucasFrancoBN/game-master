import { Component, inject } from '@angular/core';
import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { InputComponent } from "../../../../shared/components/input/input.component";
import { SelectComponent } from "../../../../shared/components/select/select.component";
import { convertStatusToEnglish, statusTranslations } from '../../utils/product-status.utils';
import { UploadComponent } from "../../../../shared/components/upload/upload.component";
import { ButtonComponent } from "../../../../shared/components/button/button.component";
import { RegisterProductService } from '../../service/register-product.service'
import { IRegisterProduct } from '../../models/register-product.model';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { LoadingComponent } from "../../../../shared/components/loading/loading.component";
import { HttpErrorResponse } from '@angular/common/http';
import { IException } from '../../../../shared/exception/exception.type';



@Component({
  selector: 'app-cadastrar-produto',
  imports: [
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzAlertModule,
    InputComponent,
    SelectComponent,
    UploadComponent,
    ButtonComponent,
    LoadingComponent
],
  templateUrl: './cadastrar-produto.component.html'
})
export class CadastrarProdutoComponent {
  private fb = inject(NonNullableFormBuilder);
  private registerProductService = inject(RegisterProductService);
  private message = inject(NzMessageService);

  loading = false;
  errorMessage = "";
  options = Object.values(statusTranslations);
  photoList: File[] = [];

  productForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(5)]],
    description: ['', [Validators.required, Validators.minLength(100)]],
    amount: [0, [Validators.required, Validators.min(0)]],
    price: [0.0, [Validators.required, Validators.min(1)]],
    weight: [0.0, [Validators.required, Validators.min(1)]],
    status: ['', Validators.required]
  });

  getError(field: string) {
    const control = this.productForm.get(field);
    
    if(control?.hasError('required'))
      return 'Esse campo é obrigatório';

    if(control?.hasError('minlength'))
      return `Mínimo de ${control.errors?.['minlength'].requiredLength} caracteres.`

    if(control?.hasError('min'))
      return `Valor mínimo de ${control.errors?.['min'].min} não atingido`

    return '';
  }
  
  get invalidForm() {
    return this.productForm.invalid; 
  }

  onSubmit() {
    this.errorMessage = "";

    if (this.invalidForm) {
      this.productForm.markAllAsTouched();
      return;
    }

    const productData: IRegisterProduct = this.productForm.getRawValue() as IRegisterProduct;
    productData.status = convertStatusToEnglish(productData.status);
    this.loading = true;

    this.registerProductService.registerProduct(productData, this.photoList).subscribe({
      next: () => {
        this.productForm.reset();
        this.photoList = [];
        this.message.success("Produto criado com sucesso");
      },
      error: (e: IException) => {
        if(e.errors?.length) this.errorMessage = e.errors.map(e => `• ${e.message}`).join('\n');
        else this.errorMessage = e.message;
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      }
    })
  }
}
