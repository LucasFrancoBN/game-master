import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzIconModule } from 'ng-zorro-antd/icon';

@Component({
  selector: 'app-breadcrumb',
  imports: [NzBreadCrumbModule, NzIconModule, RouterLink],
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent {
  routes: string[] = window.location.pathname.split("/");

  getBreadcrumbPath(index: number) {
    return '/' + this.routes.slice(0, index + 1).join('/')
  }
}
