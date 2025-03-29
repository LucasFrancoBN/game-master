import { Component, Input } from '@angular/core';

import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzIconModule } from 'ng-zorro-antd/icon';


@Component({
  selector: 'app-loading',
  imports: [NzSpinModule, NzIconModule],
  templateUrl: './loading.component.html'
})
export class LoadingComponent {
  @Input() active!: boolean;
}
