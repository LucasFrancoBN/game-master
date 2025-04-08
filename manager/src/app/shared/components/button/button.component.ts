import { Component, Input } from '@angular/core';

import { NzButtonModule, NzButtonType } from 'ng-zorro-antd/button';
import { NzSizeDSType } from 'ng-zorro-antd/core/types';

@Component({
  selector: 'app-button',
  imports: [NzButtonModule],
  templateUrl: './button.component.html',
})
export class ButtonComponent {
  @Input() disabled: boolean = false;
  @Input() type: NzButtonType = 'primary';
  @Input() size: NzSizeDSType = 'default';
  @Input() class = '';
}
