import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { NewsmonthPage } from './newsmonth';

@NgModule({
  declarations: [
    NewsmonthPage,
  ],
  imports: [
    IonicPageModule.forChild(NewsmonthPage),
  ],
})
export class NewsmonthPageModule {}
