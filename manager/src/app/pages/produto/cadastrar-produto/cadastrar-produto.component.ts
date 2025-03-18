import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-cadastrar-produto',
  imports: [ReactiveFormsModule],
  templateUrl: './cadastrar-produto.component.html'
})
export class CadastrarProdutoComponent {
  private fb = inject(FormBuilder);

  productForm = this.fb.group({
    name: ['', Validators.minLength(5)],
    description: ['', Validators.minLength(100)],
    price: [0.0, Validators.min(1)],
    weight: [0.0, Validators.min(1)],
    status: ['', ]
  })
}
