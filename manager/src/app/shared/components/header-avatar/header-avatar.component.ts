import { Component, inject, Input, OnInit } from '@angular/core';
import {
  NzAvatarComponent,
  NzAvatarGroupComponent,
} from 'ng-zorro-antd/avatar';
import { environment } from '../../../../environments/environment';
import { filter, Subject, switchMap, takeUntil, tap } from 'rxjs';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-header-avatar',
  imports: [
    NzAvatarComponent,
    NzAvatarGroupComponent,
    NzDropDownModule,
    NzIconModule,
  ],
  templateUrl: './header-avatar.component.html',
})
export class HeaderAvatarComponent implements OnInit {
  @Input() isCollapsed!: boolean;
  protected readonly environment = environment;
  private readonly authService = inject(AuthService);
  private destroy$ = new Subject<void>();

  firstLetter = '';
  firstPartOfEmail = '';
  logged = false;

  ngOnInit(): void {
    this.authService
      .isAuthenticated()
      .pipe(
        takeUntil(this.destroy$),
        tap((isLogged) => (this.logged = isLogged)),
        filter((isLogged) => isLogged),
        switchMap(() => this.authService.getMe())
      )
      .subscribe((response) => {
        this.firstLetter = response.email[0]?.toUpperCase();
        this.firstPartOfEmail = response.email.split('@')[0];
      });
  }

  handleLogout() {
    this.authService.logout();
  }
}
