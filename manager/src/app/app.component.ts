import {Component, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {SidebarAvatarComponent} from './components/sidebar-avatar/sidebar-avatar.component';
import {HeaderAvatarComponent} from './components/header-avatar/header-avatar.component';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, NzIconModule, NzLayoutModule, NzMenuModule, SidebarComponent, SidebarAvatarComponent, HeaderAvatarComponent],
  templateUrl: './app.component.html'
})
export class AppComponent {
  public isCollapsed = false;
}
