import { NgModule } from '@angular/core';
import { ComponentsModule } from './components/components.module';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        ComponentsModule
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        ComponentsModule
    ]
})
export class SharedModule { }
