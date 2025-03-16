import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzAlertModule } from 'ng-zorro-antd/alert';

@Component({
  selector: 'app-auth',
  imports: [NzIconModule, NzAlertModule],
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit {
  loading = true;
  error = "";
  private authService = inject(AuthService);
  
  constructor(private route: ActivatedRoute, private router: Router) {}
  
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const code = params.get("code");
      if(!code) return;

      this.authService.handleAccessToken(code).subscribe({
        next: () => {
          this.router.navigate(["/home"])
        },
        error: (e: Error) => {
          this.loading = false;
          console.log(e)
          this.error = "Ocorreu o seguinte erro ao logar na aplicação: " + e.message;
        }
      });
    })
  }


}
