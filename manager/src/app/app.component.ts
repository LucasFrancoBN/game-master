import {Component, inject, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {SidebarAvatarComponent} from './components/sidebar-avatar/sidebar-avatar.component';
import {HeaderAvatarComponent} from './components/header-avatar/header-avatar.component';
import { BreadcrumbComponent } from "./components/breadcrumb/breadcrumb.component";
import { AuthService } from './core/services/auth/auth.service';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, NzIconModule, NzLayoutModule, NzMenuModule, SidebarComponent, SidebarAvatarComponent, HeaderAvatarComponent, BreadcrumbComponent],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  private authService = inject(AuthService);
  public isCollapsed = false;

  ngOnInit(): void {
    this.authService.checkTokenExpiration();
  }
}
