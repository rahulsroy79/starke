import { Component } from '@angular/core';
import { App, IonicPage, ModalController, NavController, ToastController } from 'ionic-angular';
import { JhiDataUtils } from 'ng-jhipster';
import { StarkeEntity } from './starke-entity.model';
import { StarkeEntityService } from './starke-entity.provider';
import { EntitygroupPage } from '../entitygroup/entitygroup';
import { Principal } from '../../providers/auth/principal.service';
import { FirstRunPage } from '../pages';

@IonicPage({
    
})
@Component({
    selector: 'page-starke-entity-my-suffix',
    templateUrl: 'entity.html'
})
export class StarkeEntityPage {
  account: Account;
    starkeEntities: StarkeEntity[]; 
    entitygrppage  = EntitygroupPage;
    // todo: add pagination

    constructor(private dataUtils: JhiDataUtils,private navCtrl: NavController, 
        private starkeEntityService: StarkeEntityService,
        private principal: Principal, private app: App,
                private modalCtrl: ModalController, private toastCtrl: ToastController) {
        this.starkeEntities = [];
        
    }

    ionViewDidLoad() {
        this.loadAll();
    }

    loadAll(refresher?) {
        this.starkeEntityService.query().subscribe(
        // this.starkeEntityService.querybyparent(1301).subscribe(
            (response) => {
                this.starkeEntities = response;
                if (typeof(refresher) !== 'undefined') {
                    refresher.complete();
                }
            },
            (error) => {
                console.error(error);
                let toast = this.toastCtrl.create({message: 'Failed to load data', duration: 2000, position: 'middle'});
                toast.present();
            });
    }

    trackId(index: number, item: StarkeEntity) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    open(slidingItem: any, item: StarkeEntity) {
        let modal = this.modalCtrl.create('StarkeEntityMySuffixDialogPage', {item: item});
        modal.onDidDismiss(starkeEntity => {
            if (starkeEntity) {
                if (starkeEntity.id) {
                    this.starkeEntityService.update(starkeEntity).subscribe(data => {
                        this.loadAll();
                        let toast = this.toastCtrl.create(
                            {message: 'StarkeEntityMySuffix updated successfully.', duration: 3000, position: 'middle'});
                        toast.present();
                        slidingItem.close();
                    }, (error) => console.error(error));
                } else {
                    this.starkeEntityService.create(starkeEntity).subscribe(data => {
                        this.starkeEntities.push(data);
                        let toast = this.toastCtrl.create(
                            {message: 'StarkeEntityMySuffix added successfully.', duration: 3000, position: 'middle'});
                        toast.present();
                    }, (error) => console.error(error));
                }
            }
        });
        modal.present();
    }

    delete(starkeEntity) {
        this.starkeEntityService.delete(starkeEntity.id).subscribe(() => {
            let toast = this.toastCtrl.create(
                {message: 'StarkeEntityMySuffix deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    detail(starkeEntity: StarkeEntity) {
        this.navCtrl.push('StarkeEntityMySuffixDetailPage', {id: starkeEntity.id});
    }

    navigatepage(trackId : any){
      console.log("ID _- "+trackId);
      this.navCtrl.push(this.entitygrppage);
    }

    ngOnInit() {
      this.principal.identity().then((account) => {
        if (account === null) {
          this.app.getRootNavs()[0].setRoot(FirstRunPage);
        } else {
          this.account = account;
        }
      });
    }
  
    isAuthenticated() {
      return this.principal.isAuthenticated();
    }
}
