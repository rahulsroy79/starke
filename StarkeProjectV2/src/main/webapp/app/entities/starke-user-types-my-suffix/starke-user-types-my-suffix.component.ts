import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';
import { Principal } from 'app/core';
import { StarkeUserTypesMySuffixService } from './starke-user-types-my-suffix.service';

@Component({
    selector: 'jhi-starke-user-types-my-suffix',
    templateUrl: './starke-user-types-my-suffix.component.html'
})
export class StarkeUserTypesMySuffixComponent implements OnInit, OnDestroy {
    starkeUserTypes: IStarkeUserTypesMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private starkeUserTypesService: StarkeUserTypesMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.starkeUserTypesService.query().subscribe(
            (res: HttpResponse<IStarkeUserTypesMySuffix[]>) => {
                this.starkeUserTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStarkeUserTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStarkeUserTypesMySuffix) {
        return item.id;
    }

    registerChangeInStarkeUserTypes() {
        this.eventSubscriber = this.eventManager.subscribe('starkeUserTypesListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
