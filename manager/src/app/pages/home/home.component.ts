import { Component } from '@angular/core';
import {GameMasterSvgComponent} from '../../components/svg/game-master-svg/game-master-svg.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  imports: [
    GameMasterSvgComponent
  ]
})
export class HomeComponent {
  constructor() {}
}
