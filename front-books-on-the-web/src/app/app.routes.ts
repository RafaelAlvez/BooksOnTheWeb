import { Routes } from '@angular/router';
import { LivrosComponent } from './livros/livros.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { TelaInicialComponent } from './tela-inicial/tela-inicial.component';
import { EmprestimosComponent } from './emprestimos/emprestimos.component';
import { PenalidadesComponent } from './penalidades/penalidades.component';

export const routes: Routes = [
  { path: '', component: TelaInicialComponent },
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'livros', component: LivrosComponent },
  { path: 'emprestimos', component: EmprestimosComponent },
  { path: 'penalidades', component: PenalidadesComponent },
];
