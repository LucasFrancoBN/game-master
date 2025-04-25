import { Routes } from '@angular/router';
import { CadastrarProdutoComponent } from './pages/cadastrar-produto/cadastrar-produto.component';
import { ListarProdutosComponent } from './pages/listar-produtos/listar-produtos.component';
import { ObterProdutoPorIdComponent } from './pages/obter-produto-por-id/obter-produto-por-id.component';
import { EditarProdutoComponent } from './pages/editar-produto/editar-produto.component';
import { EditarImagensComponent } from './pages/editar-imagens/editar-imagens.component';
import { AdicionarImagensComponent } from './pages/adicionar-imagens/adicionar-imagens.component';

export const productRoutes: Routes = [
  { path: 'cadastrar-produto', component: CadastrarProdutoComponent },
  {
    path: 'listar-produtos',
    children: [
      { path: '', component: ListarProdutosComponent },
      { path: ':id', component: ObterProdutoPorIdComponent },
      { path: 'editar-produto/:id', component: EditarProdutoComponent },
      { path: 'editar-imagens/:id', component: EditarImagensComponent },
      { path: 'adicionar-imagens/:id', component: AdicionarImagensComponent },
    ],
  },
];
