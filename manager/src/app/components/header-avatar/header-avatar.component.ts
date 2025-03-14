import {Component, Input} from '@angular/core';
import {NzAvatarComponent, NzAvatarGroupComponent} from "ng-zorro-antd/avatar";
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-header-avatar',
    imports: [
        NzAvatarComponent,
        NzAvatarGroupComponent
    ],
  templateUrl: './header-avatar.component.html',
  styleUrl: './header-avatar.component.css'
})
export class HeaderAvatarComponent {
  @Input() isCollapsed!: boolean;
  protected readonly environment = environment;
}
