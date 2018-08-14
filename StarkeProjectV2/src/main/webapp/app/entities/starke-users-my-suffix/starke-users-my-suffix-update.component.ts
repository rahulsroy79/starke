import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';
import { StarkeUsersMySuffixService } from './starke-users-my-suffix.service';
import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';
import { StarkeUserTypesMySuffixService } from 'app/entities/starke-user-types-my-suffix';

@Component({
    selector: 'jhi-starke-users-my-suffix-update',
    templateUrl: './starke-users-my-suffix-update.component.html'
})
export class StarkeUsersMySuffixUpdateComponent implements OnInit {
    private _starkeUsers: IStarkeUsersMySuffix;
    isSaving: boolean;

    starkeusertypes: IStarkeUserTypesMySuffix[];
    activesinceDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private starkeUsersService: StarkeUsersMySuffixService,
        private starkeUserTypesService: StarkeUserTypesMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ starkeUsers }) => {
            this.starkeUsers = starkeUsers;
        });
        this.starkeUserTypesService.query({ filter: 'starkeuser-is-null' }).subscribe(
            (res: HttpResponse<IStarkeUserTypesMySuffix[]>) => {
                if (!this.starkeUsers.starkeUserTypesId) {
                    this.starkeusertypes = res.body;
                } else {
                    this.starkeUserTypesService.find(this.starkeUsers.starkeUserTypesId).subscribe(
                        (subRes: HttpResponse<IStarkeUserTypesMySuffix>) => {
                            this.starkeusertypes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.starkeUsers.id !== undefined) {
            this.subscribeToSaveResponse(this.starkeUsersService.update(this.starkeUsers));
        } else {
            this.subscribeToSaveResponse(this.starkeUsersService.create(this.starkeUsers));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStarkeUsersMySuffix>>) {
        result.subscribe((res: HttpResponse<IStarkeUsersMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackStarkeUserTypesById(index: number, item: IStarkeUserTypesMySuffix) {
        return item.id;
    }
    get starkeUsers() {
        return this._starkeUsers;
    }

    set starkeUsers(starkeUsers: IStarkeUsersMySuffix) {
        this._starkeUsers = starkeUsers;
    }
}
