import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from '../primeng-imports';
import { PipeModule } from './pipes/pipe.module';
import { TabelaPadraoComponent } from './tabela-padrao/tabela-padrao.component';

@NgModule({
  declarations: [
    TabelaPadraoComponent
  ],
  imports: [
    CommonModule,
    PRIMENG_IMPORTS,
    PipeModule
  ],
  exports: [
    TabelaPadraoComponent
  ]
})
export class ComponentsModule { }
