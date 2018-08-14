import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';
import { EntityReviewsMySuffixService } from './entity-reviews-my-suffix.service';
import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from 'app/entities/starke-entity-my-suffix';

@Component({
    selector: 'jhi-entity-reviews-my-suffix-update',
    templateUrl: './entity-reviews-my-suffix-update.component.html'
})
export class EntityReviewsMySuffixUpdateComponent implements OnInit {
    private _entityReviews: IEntityReviewsMySuffix;
    isSaving: boolean;

    starkeentities: IStarkeEntityMySuffix[];
    reviewdateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private entityReviewsService: EntityReviewsMySuffixService,
        private starkeEntityService: StarkeEntityMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ entityReviews }) => {
            this.entityReviews = entityReviews;
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
        if (this.entityReviews.id !== undefined) {
            this.subscribeToSaveResponse(this.entityReviewsService.update(this.entityReviews));
        } else {
            this.subscribeToSaveResponse(this.entityReviewsService.create(this.entityReviews));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEntityReviewsMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEntityReviewsMySuffix>) => this.onSaveSuccess(),
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
    get entityReviews() {
        return this._entityReviews;
    }

    set entityReviews(entityReviews: IEntityReviewsMySuffix) {
        this._entityReviews = entityReviews;
    }
}
