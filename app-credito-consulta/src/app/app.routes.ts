import { Routes } from '@angular/router';
import { ConsultaComponent } from './credito-consulta/consulta/consulta.component';
import { CadastroComponent } from './credito-consulta/cadastro/cadastro.component';
import { EditarComponent } from './credito-consulta/editar/editar.component';

export const routes: Routes = [
  {
    path: '',
    component: ConsultaComponent,
  },
  {
    path: 'cadastro',
    component: CadastroComponent,
  },
  {
    path: 'editar/:numeroCredito',
    component: EditarComponent,
  },
];
