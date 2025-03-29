import { Component, Input } from '@angular/core';

import { NzButtonModule } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-button',
  imports: [NzButtonModule],
  templateUrl: './button.component.html'
})
export class ButtonComponent {
  @Input() disabled: boolean = false;
}
