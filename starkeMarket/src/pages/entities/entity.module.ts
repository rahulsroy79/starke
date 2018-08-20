import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';
import { StarkeEntityPage } from './entity';
import { StarkeEntityService } from './starke-entity.provider';

@NgModule({
    declarations: [
        StarkeEntityPage
    ],
    imports: [
        IonicPageModule.forChild(StarkeEntityPage),
        TranslateModule.forChild()
    ],
    exports: [
        StarkeEntityPage
    ],
    providers: [StarkeEntityService]
})
export class StarkeEntityPageModule {
}