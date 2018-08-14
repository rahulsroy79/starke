import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Starkev2SharedModule } from 'app/shared';
import {
    StarkeUsersMySuffixComponent,
    StarkeUsersMySuffixDetailComponent,
    StarkeUsersMySuffixUpdateComponent,
    StarkeUsersMySuffixDeletePopupComponent,
    StarkeUsersMySuffixDeleteDialogComponent,
    starkeUsersRoute,
    starkeUsersPopupRoute
} from './';

const ENTITY_STATES = [...starkeUsersRoute, ...starkeUsersPopupRoute];

@NgModule({
    imports: [Starkev2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StarkeUsersMySuffixComponent,
        StarkeUsersMySuffixDetailComponent,
        StarkeUsersMySuffixUpdateComponent,
        StarkeUsersMySuffixDeleteDialogComponent,
        StarkeUsersMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StarkeUsersMySuffixComponent,
        StarkeUsersMySuffixUpdateComponent,
        StarkeUsersMySuffixDeleteDialogComponent,
        StarkeUsersMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2StarkeUsersMySuffixModule {}
