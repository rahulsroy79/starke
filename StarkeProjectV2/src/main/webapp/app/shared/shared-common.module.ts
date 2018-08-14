import { NgModule } from '@angular/core';

import { Starkev2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [Starkev2SharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [Starkev2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class Starkev2SharedCommonModule {}
