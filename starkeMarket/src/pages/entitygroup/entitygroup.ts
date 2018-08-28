import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { NewsmonthPage } from '../newsmonth/newsmonth';
import { EntitylistPage } from '../entitylist/entitylist';

/**
 * Generated class for the EntitygroupPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-entitygroup',
  templateUrl: 'entitygroup.html',
})
export class EntitygroupPage {
  newsmonthPage = NewsmonthPage;
  entitylistPage = EntitylistPage;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EntitygroupPage');
  }

  navNewsByMonth(){
    this.navCtrl.push(this.newsmonthPage);
  }

  getTrendingEntityList(){
    this.navCtrl.push(this.entitylistPage);
  }

}
