import {Component, Input} from '@angular/core';
import {GameMasterSvgComponent} from '../svg/game-master-svg/game-master-svg.component';
import {NzIconDirective} from 'ng-zorro-antd/icon';
import {NzMenuDirective, NzMenuItemComponent, NzSubMenuComponent} from 'ng-zorro-antd/menu';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [
    GameMasterSvgComponent,
    NzIconDirective,
    NzMenuDirective,
    NzMenuItemComponent,
    NzSubMenuComponent,
    RouterLink
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  @Input() public isCollapsed!: boolean;
}
