import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Starkev2StarkeEntityMySuffixModule } from './starke-entity-my-suffix/starke-entity-my-suffix.module';
import { Starkev2EntityPageDetailsMySuffixModule } from './entity-page-details-my-suffix/entity-page-details-my-suffix.module';
import { Starkev2EntityPageFormatsMySuffixModule } from './entity-page-formats-my-suffix/entity-page-formats-my-suffix.module';
import { Starkev2EntityDetailsMySuffixModule } from './entity-details-my-suffix/entity-details-my-suffix.module';
import { Starkev2EntityReviewsMySuffixModule } from './entity-reviews-my-suffix/entity-reviews-my-suffix.module';
import { Starkev2StarkeUsersMySuffixModule } from './starke-users-my-suffix/starke-users-my-suffix.module';
import { Starkev2StarkeUserTypesMySuffixModule } from './starke-user-types-my-suffix/starke-user-types-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Starkev2StarkeEntityMySuffixModule,
        Starkev2EntityPageDetailsMySuffixModule,
        Starkev2EntityPageFormatsMySuffixModule,
        Starkev2EntityDetailsMySuffixModule,
        Starkev2EntityReviewsMySuffixModule,
        Starkev2StarkeUsersMySuffixModule,
        Starkev2StarkeUserTypesMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starkev2EntityModule {}
