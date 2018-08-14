import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    StarkeUserTypesMySuffixComponent,
    StarkeUserTypesMySuffixDetailComponent,
    StarkeUserTypesMySuffixUpdateComponent,
    StarkeUserTypesMySuffixDeletePopupComponent,
    StarkeUserTypesMySuffixDeleteDialogComponent,
    starkeUserTypesRoute,
    starkeUserTypesPopupRoute
} from './';

const ENTITY_STATES = [...starkeUserTypesRoute, ...starkeUserTypesPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StarkeUserTypesMySuffixComponent,
        StarkeUserTypesMySuffixDetailComponent,
        StarkeUserTypesMySuffixUpdateComponent,
        StarkeUserTypesMySuffixDeleteDialogComponent,
        StarkeUserTypesMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StarkeUserTypesMySuffixComponent,
        StarkeUserTypesMySuffixUpdateComponent,
        StarkeUserTypesMySuffixDeleteDialogComponent,
        StarkeUserTypesMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2StarkeUserTypesMySuffixModule {}
