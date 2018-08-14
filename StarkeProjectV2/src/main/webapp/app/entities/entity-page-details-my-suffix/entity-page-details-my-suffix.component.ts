import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { Principal } from 'app/core';
import { EntityPageDetailsMySuffixService } from './entity-page-details-my-suffix.service';

@Component({
    selector: 'jhi-entity-page-details-my-suffix',
    templateUrl: './entity-page-details-my-suffix.component.html'
})
export class EntityPageDetailsMySuffixComponent implements OnInit, OnDestroy {
    entityPageDetails: IEntityPageDetailsMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private entityPageDetailsService: EntityPageDetailsMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.entityPageDetailsService.query().subscribe(
            (res: HttpResponse<IEntityPageDetailsMySuffix[]>) => {
                this.entityPageDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEntityPageDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEntityPageDetailsMySuffix) {
        return item.id;
    }

    registerChangeInEntityPageDetails() {
        this.eventSubscriber = this.eventManager.subscribe('entityPageDetailsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
