import { Routes } from '@angular/router';
import { CadastrarProdutoComponent } from './pages/cadastrar-produto/cadastrar-produto.component';
import { ListarProdutosComponent } from './pages/listar-produtos/listar-produtos.component';

export const productRoutes: Routes = [
  { path: 'cadastrar-produto', component: CadastrarProdutoComponent },
  { path: 'listar-produtos', component: ListarProdutosComponent },
];
