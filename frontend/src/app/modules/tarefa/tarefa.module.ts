import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { TarefaComponent } from './componente/tarefa.component';
import { TarefaRoutingModule } from './tarefa-routing.module';

@NgModule({
  declarations: [TarefaComponent],
  imports: [
    CommonModule,
    TarefaRoutingModule,
    SharedModule
  ]
})
export class TarefaModule { }
