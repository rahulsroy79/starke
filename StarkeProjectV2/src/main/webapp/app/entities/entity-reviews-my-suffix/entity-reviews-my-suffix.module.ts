import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    EntityReviewsMySuffixComponent,
    EntityReviewsMySuffixDetailComponent,
    EntityReviewsMySuffixUpdateComponent,
    EntityReviewsMySuffixDeletePopupComponent,
    EntityReviewsMySuffixDeleteDialogComponent,
    entityReviewsRoute,
    entityReviewsPopupRoute
} from './';

const ENTITY_STATES = [...entityReviewsRoute, ...entityReviewsPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntityReviewsMySuffixComponent,
        EntityReviewsMySuffixDetailComponent,
        EntityReviewsMySuffixUpdateComponent,
        EntityReviewsMySuffixDeleteDialogComponent,
        EntityReviewsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EntityReviewsMySuffixComponent,
        EntityReviewsMySuffixUpdateComponent,
        EntityReviewsMySuffixDeleteDialogComponent,
        EntityReviewsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2EntityReviewsMySuffixModule {}
