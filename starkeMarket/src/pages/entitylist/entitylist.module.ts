import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EntitylistPage } from './entitylist';

@NgModule({
  declarations: [
    EntitylistPage,
  ],
  imports: [
    IonicPageModule.forChild(EntitylistPage),
  ],
})
export class EntitylistPageModule {}
