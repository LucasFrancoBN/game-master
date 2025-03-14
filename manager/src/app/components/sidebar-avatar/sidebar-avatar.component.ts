import {Component, Input} from '@angular/core';
import {NzAvatarComponent, NzAvatarGroupComponent} from "ng-zorro-antd/avatar";

@Component({
  selector: 'app-sidebar-avatar',
    imports: [
        NzAvatarComponent,
        NzAvatarGroupComponent
    ],
  templateUrl: './sidebar-avatar.component.html',
  styleUrl: './sidebar-avatar.component.css'
})
export class SidebarAvatarComponent {
  @Input() isCollapsed!: boolean;
}
