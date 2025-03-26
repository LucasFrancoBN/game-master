import { Routes } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import { AuthComponent } from './pages/auth/auth.component';
import { CadastrarProdutoComponent } from './pages/produto/cadastrar-produto/cadastrar-produto.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'produto', children: [ { path: 'cadastrar-produto', component: CadastrarProdutoComponent } ] }
];
