import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-game-master-svg',
  imports: [],
  templateUrl: './game-master-svg.component.html'
})
export class GameMasterSvgComponent {
  @Input() color: string = '#F5F5F5';
  @Input() size: number = 45;
}
