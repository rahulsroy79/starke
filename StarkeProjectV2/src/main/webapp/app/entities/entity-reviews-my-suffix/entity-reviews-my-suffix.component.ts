import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';
import { Principal } from 'app/core';
import { EntityReviewsMySuffixService } from './entity-reviews-my-suffix.service';

@Component({
    selector: 'jhi-entity-reviews-my-suffix',
    templateUrl: './entity-reviews-my-suffix.component.html'
})
export class EntityReviewsMySuffixComponent implements OnInit, OnDestroy {
    entityReviews: IEntityReviewsMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private entityReviewsService: EntityReviewsMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.entityReviewsService.query().subscribe(
            (res: HttpResponse<IEntityReviewsMySuffix[]>) => {
                this.entityReviews = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEntityReviews();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEntityReviewsMySuffix) {
        return item.id;
    }

    registerChangeInEntityReviews() {
        this.eventSubscriber = this.eventManager.subscribe('entityReviewsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
