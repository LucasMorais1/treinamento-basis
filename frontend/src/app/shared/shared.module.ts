import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComponentsModule } from './components/components.module';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        ComponentsModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        ComponentsModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class SharedModule { }
