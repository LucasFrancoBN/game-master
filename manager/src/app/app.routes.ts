import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/pages/home.component';
import { AuthComponent } from './features/auth/pages/auth.component';


export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'produto', loadChildren: () => import('./features/product/product.routes').then(m => m.productRoutes) }
];
