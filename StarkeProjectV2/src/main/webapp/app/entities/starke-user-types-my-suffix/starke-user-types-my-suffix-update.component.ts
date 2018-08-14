import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';
import { StarkeUserTypesMySuffixService } from './starke-user-types-my-suffix.service';
import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';
import { StarkeUsersMySuffixService } from 'app/entities/starke-users-my-suffix';

@Component({
    selector: 'jhi-starke-user-types-my-suffix-update',
    templateUrl: './starke-user-types-my-suffix-update.component.html'
})
export class StarkeUserTypesMySuffixUpdateComponent implements OnInit {
    private _starkeUserTypes: IStarkeUserTypesMySuffix;
    isSaving: boolean;

    starkeusers: IStarkeUsersMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private starkeUserTypesService: StarkeUserTypesMySuffixService,
        private starkeUsersService: StarkeUsersMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ starkeUserTypes }) => {
            this.starkeUserTypes = starkeUserTypes;
        });
        this.starkeUsersService.query().subscribe(
            (res: HttpResponse<IStarkeUsersMySuffix[]>) => {
                this.starkeusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.starkeUserTypes.id !== undefined) {
            this.subscribeToSaveResponse(this.starkeUserTypesService.update(this.starkeUserTypes));
        } else {
            this.subscribeToSaveResponse(this.starkeUserTypesService.create(this.starkeUserTypes));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStarkeUserTypesMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IStarkeUserTypesMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackStarkeUsersById(index: number, item: IStarkeUsersMySuffix) {
        return item.id;
    }
    get starkeUserTypes() {
        return this._starkeUserTypes;
    }

    set starkeUserTypes(starkeUserTypes: IStarkeUserTypesMySuffix) {
        this._starkeUserTypes = starkeUserTypes;
    }
}
