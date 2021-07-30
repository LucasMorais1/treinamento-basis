import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TarefaComponent } from './componente/tarefa.component';

const routes: Routes = [
  { path: '', component: TarefaComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TarefaRoutingModule { }
