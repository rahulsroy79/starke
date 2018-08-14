import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    EntityPageDetailsMySuffixComponent,
    EntityPageDetailsMySuffixDetailComponent,
    EntityPageDetailsMySuffixUpdateComponent,
    EntityPageDetailsMySuffixDeletePopupComponent,
    EntityPageDetailsMySuffixDeleteDialogComponent,
    entityPageDetailsRoute,
    entityPageDetailsPopupRoute
} from './';

const ENTITY_STATES = [...entityPageDetailsRoute, ...entityPageDetailsPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntityPageDetailsMySuffixComponent,
        EntityPageDetailsMySuffixDetailComponent,
        EntityPageDetailsMySuffixUpdateComponent,
        EntityPageDetailsMySuffixDeleteDialogComponent,
        EntityPageDetailsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EntityPageDetailsMySuffixComponent,
        EntityPageDetailsMySuffixUpdateComponent,
        EntityPageDetailsMySuffixDeleteDialogComponent,
        EntityPageDetailsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2EntityPageDetailsMySuffixModule {}
