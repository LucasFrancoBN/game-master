import { Component } from '@angular/core';
import {NavigationEnd, Router, RouterLink} from '@angular/router';

import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { filter } from 'rxjs';

@Component({
  selector: 'app-breadcrumb',
  imports: [NzBreadCrumbModule, NzIconModule, RouterLink],
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent {
  routes: string[] = [];

  constructor(private router: Router) {
    this.router.events
    .pipe(filter(event => event instanceof NavigationEnd))
    .subscribe(() => {
      this.routes = this.router.url.split('/');
    });
  }

  getBreadcrumbPath(index: number) {
    return '/' + this.routes.slice(0, index + 1).join('/')
  }
}
