import { Component, inject } from '@angular/core';
import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { InputComponent } from "../../../components/input/input.component";
import { SelectComponent } from "../../../components/select/select.component";
import { statusTranslations } from '../../../utils/product-status.utils';
import { UploadComponent } from "../../../components/upload/upload.component";
import { ButtonComponent } from "../../../components/button/button.component";

@Component({
  selector: 'app-cadastrar-produto',
  imports: [ReactiveFormsModule, NzFormModule, NzInputModule, InputComponent, SelectComponent, UploadComponent, ButtonComponent],
  templateUrl: './cadastrar-produto.component.html'
})
export class CadastrarProdutoComponent {
  private fb = inject(NonNullableFormBuilder);
  options = Object.values(statusTranslations);
  photoList: ArrayBuffer[] = [];

  productForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(5)]],
    description: ['', [Validators.required, Validators.minLength(100)]],
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
  

  onSubmit() {
    if (this.productForm.invalid || !this.photoList.length) {
      console.log(this.productForm)
      console.log("Inválido");
      return;
    }
    console.log(this.productForm.invalid)
    console.log(this.productForm);
    console.log(this.photoList);
  }
}
