import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    EntityDetailsMySuffixComponent,
    EntityDetailsMySuffixDetailComponent,
    EntityDetailsMySuffixUpdateComponent,
    EntityDetailsMySuffixDeletePopupComponent,
    EntityDetailsMySuffixDeleteDialogComponent,
    entityDetailsRoute,
    entityDetailsPopupRoute
} from './';

const ENTITY_STATES = [...entityDetailsRoute, ...entityDetailsPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntityDetailsMySuffixComponent,
        EntityDetailsMySuffixDetailComponent,
        EntityDetailsMySuffixUpdateComponent,
        EntityDetailsMySuffixDeleteDialogComponent,
        EntityDetailsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EntityDetailsMySuffixComponent,
        EntityDetailsMySuffixUpdateComponent,
        EntityDetailsMySuffixDeleteDialogComponent,
        EntityDetailsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2EntityDetailsMySuffixModule {}
