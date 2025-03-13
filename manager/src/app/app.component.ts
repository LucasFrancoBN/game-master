import {Component, signal} from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import {GameMasterSvgComponent} from './components/svg/game-master-svg/game-master-svg.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, NzIconModule, NzLayoutModule, NzMenuModule, SidebarComponent],
  templateUrl: './app.component.html'
})
export class AppComponent {
  public isCollapsed = false;
}
