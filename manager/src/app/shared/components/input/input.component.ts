import { Component, Input } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  ReactiveFormsModule,
} from '@angular/forms';

import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';

@Component({
  standalone: true,
  selector: 'app-input',
  imports: [ReactiveFormsModule, NzFormModule, NzInputModule],
  templateUrl: './input.component.html',
})
export class InputComponent {
  @Input() id!: string;
  @Input() label?: string;
  @Input() type = 'text';
  @Input() control!: AbstractControl;
  @Input() class = '';
  @Input() errorTip!: string;
  @Input() placeholder?: string;

  public get formControl(): FormControl {
    return this.control as FormControl;
  }

  public get hasError(): boolean {
    return this.formControl.invalid && this.formControl.touched;
  }
}
