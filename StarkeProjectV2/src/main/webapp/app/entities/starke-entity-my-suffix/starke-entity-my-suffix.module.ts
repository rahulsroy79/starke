import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    StarkeEntityMySuffixComponent,
    StarkeEntityMySuffixDetailComponent,
    StarkeEntityMySuffixUpdateComponent,
    StarkeEntityMySuffixDeletePopupComponent,
    StarkeEntityMySuffixDeleteDialogComponent,
    starkeEntityRoute,
    starkeEntityPopupRoute
} from './';

const ENTITY_STATES = [...starkeEntityRoute, ...starkeEntityPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StarkeEntityMySuffixComponent,
        StarkeEntityMySuffixDetailComponent,
        StarkeEntityMySuffixUpdateComponent,
        StarkeEntityMySuffixDeleteDialogComponent,
        StarkeEntityMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StarkeEntityMySuffixComponent,
        StarkeEntityMySuffixUpdateComponent,
        StarkeEntityMySuffixDeleteDialogComponent,
        StarkeEntityMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2StarkeEntityMySuffixModule {}
