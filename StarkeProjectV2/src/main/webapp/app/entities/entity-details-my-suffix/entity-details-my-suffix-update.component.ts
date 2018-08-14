import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';
import { EntityDetailsMySuffixService } from './entity-details-my-suffix.service';
import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from 'app/entities/starke-entity-my-suffix';

@Component({
    selector: 'jhi-entity-details-my-suffix-update',
    templateUrl: './entity-details-my-suffix-update.component.html'
})
export class EntityDetailsMySuffixUpdateComponent implements OnInit {
    private _entityDetails: IEntityDetailsMySuffix;
    isSaving: boolean;

    starkeentities: IStarkeEntityMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private entityDetailsService: EntityDetailsMySuffixService,
        private starkeEntityService: StarkeEntityMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ entityDetails }) => {
            this.entityDetails = entityDetails;
        });
        this.starkeEntityService.query().subscribe(
            (res: HttpResponse<IStarkeEntityMySuffix[]>) => {
                this.starkeentities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.entityDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.entityDetailsService.update(this.entityDetails));
        } else {
            this.subscribeToSaveResponse(this.entityDetailsService.create(this.entityDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEntityDetailsMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEntityDetailsMySuffix>) => this.onSaveSuccess(),
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

    trackStarkeEntityById(index: number, item: IStarkeEntityMySuffix) {
        return item.id;
    }
    get entityDetails() {
        return this._entityDetails;
    }

    set entityDetails(entityDetails: IEntityDetailsMySuffix) {
        this._entityDetails = entityDetails;
    }
}
