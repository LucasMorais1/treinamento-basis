import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { DinamicoPipe } from './dinamico.pipe';



@NgModule({
  declarations: [
    DinamicoPipe
  ],
  imports: [
    CommonModule
  ],
  providers: [
    DinamicoPipe
  ],
  exports: [
    DinamicoPipe
  ]
})
export class PipeModule { }
