import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { NewsmonthPage } from '../newsmonth/newsmonth';

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
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EntitygroupPage');
  }

  navNewsByMonth(){
    this.navCtrl.push(this.newsmonthPage);
  }

}
