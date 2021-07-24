import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { ResponsavelComponent } from './componente/responsavel.component';
import { ResponsavelRoutingModule } from './responsavel-routing.module';

@NgModule({
  declarations: [
    ResponsavelComponent
  ],
  imports: [
    CommonModule,
    ResponsavelRoutingModule,
    SharedModule
  ]
})
export class ResponsavelModule { }
