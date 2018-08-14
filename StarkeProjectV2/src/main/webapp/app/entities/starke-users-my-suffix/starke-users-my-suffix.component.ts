import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';
import { Principal } from 'app/core';
import { StarkeUsersMySuffixService } from './starke-users-my-suffix.service';

@Component({
    selector: 'jhi-starke-users-my-suffix',
    templateUrl: './starke-users-my-suffix.component.html'
})
export class StarkeUsersMySuffixComponent implements OnInit, OnDestroy {
    starkeUsers: IStarkeUsersMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private starkeUsersService: StarkeUsersMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.starkeUsersService.query().subscribe(
            (res: HttpResponse<IStarkeUsersMySuffix[]>) => {
                this.starkeUsers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStarkeUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStarkeUsersMySuffix) {
        return item.id;
    }

    registerChangeInStarkeUsers() {
        this.eventSubscriber = this.eventManager.subscribe('starkeUsersListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
