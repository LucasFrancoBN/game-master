import { Component, Input } from '@angular/core';

import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzFormModule } from 'ng-zorro-antd/form';
import { AbstractControl, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-select',
  imports: [NzSelectModule, NzFormModule, ReactiveFormsModule],
  templateUrl: './select.component.html'
})
export class SelectComponent {
  @Input() options!: string[]
  @Input() label!: string;
  @Input() errorTip!: string
  @Input() control!:AbstractControl;

    public get formControl() : FormControl {
      return this.control as FormControl
    }

  public get hasError() : boolean {
    return this.formControl.invalid && this.formControl.touched;
  }
}
