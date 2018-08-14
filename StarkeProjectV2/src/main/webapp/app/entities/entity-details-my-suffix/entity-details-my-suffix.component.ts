import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';
import { Principal } from 'app/core';
import { EntityDetailsMySuffixService } from './entity-details-my-suffix.service';

@Component({
    selector: 'jhi-entity-details-my-suffix',
    templateUrl: './entity-details-my-suffix.component.html'
})
export class EntityDetailsMySuffixComponent implements OnInit, OnDestroy {
    entityDetails: IEntityDetailsMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private entityDetailsService: EntityDetailsMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.entityDetailsService.query().subscribe(
            (res: HttpResponse<IEntityDetailsMySuffix[]>) => {
                this.entityDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEntityDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEntityDetailsMySuffix) {
        return item.id;
    }

    registerChangeInEntityDetails() {
        this.eventSubscriber = this.eventManager.subscribe('entityDetailsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
