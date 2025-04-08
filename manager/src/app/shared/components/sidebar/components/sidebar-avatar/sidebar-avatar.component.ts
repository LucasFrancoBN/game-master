import { Component, inject, Input } from '@angular/core';
import {
  NzAvatarComponent,
  NzAvatarGroupComponent,
} from 'ng-zorro-antd/avatar';
import { environment } from '../../../../../../environments/environment';
import { takeUntil, filter, switchMap, Subject, tap } from 'rxjs';
import { AuthService } from '../../../../../core/services/auth.service';

@Component({
  selector: 'app-sidebar-avatar',
  imports: [NzAvatarComponent, NzAvatarGroupComponent],
  templateUrl: './sidebar-avatar.component.html',
})
export class SidebarAvatarComponent {
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
}
