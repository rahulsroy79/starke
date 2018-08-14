import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    EntityPageFormatsMySuffixComponent,
    EntityPageFormatsMySuffixDetailComponent,
    EntityPageFormatsMySuffixUpdateComponent,
    EntityPageFormatsMySuffixDeletePopupComponent,
    EntityPageFormatsMySuffixDeleteDialogComponent,
    entityPageFormatsRoute,
    entityPageFormatsPopupRoute
} from './';

const ENTITY_STATES = [...entityPageFormatsRoute, ...entityPageFormatsPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntityPageFormatsMySuffixComponent,
        EntityPageFormatsMySuffixDetailComponent,
        EntityPageFormatsMySuffixUpdateComponent,
        EntityPageFormatsMySuffixDeleteDialogComponent,
        EntityPageFormatsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EntityPageFormatsMySuffixComponent,
        EntityPageFormatsMySuffixUpdateComponent,
        EntityPageFormatsMySuffixDeleteDialogComponent,
        EntityPageFormatsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2EntityPageFormatsMySuffixModule {}
