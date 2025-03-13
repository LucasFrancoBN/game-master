import {Component, signal} from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import {GameMasterSvgComponent} from './components/svg/game-master-svg/game-master-svg.component';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, NzIconModule, NzLayoutModule, NzMenuModule,GameMasterSvgComponent, RouterLink],
  templateUrl: './app.component.html'
})
export class AppComponent {
  public isCollapsed = false;
}
